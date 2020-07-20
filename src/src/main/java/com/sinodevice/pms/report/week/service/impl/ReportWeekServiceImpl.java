package com.sinodevice.pms.report.week.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.utils.BigDecimalUtils;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.report.week.dto.ReportWeekDto;
import com.sinodevice.pms.report.week.entity.ReportWeek;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.week.mapper.ReportWeekMapper;
import com.sinodevice.pms.report.week.service.IReportWeekDetailsService;
import com.sinodevice.pms.report.week.service.IReportWeekService;
import com.sinodevice.pms.sys.param.entity.Param;
import com.sinodevice.pms.sys.param.service.IParamService;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
@Service
public class ReportWeekServiceImpl extends ServiceImpl<ReportWeekMapper, ReportWeek> implements IReportWeekService {

    @Autowired
    private IReportWeekDetailsService reportWeekDetailsService;

    @Autowired
    private IParamService paramService;

    @Autowired
    private IUserPostService userPostService;

    @Override
    public IPage<ReportWeek> page(IPage<ReportWeek> page, ReportWeek reportWeek) {
        return page.setRecords(this.baseMapper.page(page, reportWeek));
    }

    @Override
    public IPage<ReportWeek> pageTotal(IPage<ReportWeek> page, ReportWeek reportWeek) {
        return page.setRecords(this.baseMapper.pageTotal(page, reportWeek));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveDto(ReportWeekDto reportWeekDto) {
        LocalDate today = LocalDate.now();
        Param startWeekParam = paramService.getByCode("start.week");
        BigDecimal workHour = BigDecimal.ZERO;

        //获取当前是第几周
        int realWeekOfYear = today.get(WeekFields.of(DayOfWeek.MONDAY, 1).weekOfYear());
        reportWeekDto.setWeekIndex(realWeekOfYear - Integer.parseInt(startWeekParam.getContent()));
        reportWeekDto.setUserId(LoginHelper.getAccount().getId());

        reportWeekDto.setBeginDate(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));
        reportWeekDto.setEndDate(today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));

        int count = this.count(Wrappers.<ReportWeek>lambdaQuery().eq(ReportWeek::getWeekIndex, reportWeekDto.getWeekIndex()).eq(ReportWeek::getUserId, reportWeekDto.getUserId()));

        Assert.fail(count > 0, "已存在第" + reportWeekDto.getWeekIndex() + "工作周报,请勿重复提交!");

        Boolean result = super.save(reportWeekDto);
        for (ReportWeekDetails reportWeekDetails : reportWeekDto.getReportWeekDetailsList()) {
            reportWeekDetails.setReportId(reportWeekDto.getId());
            workHour = BigDecimalUtils.add(workHour, reportWeekDetails.getWorkHour());
        }

        this.update(Wrappers.<ReportWeek>lambdaUpdate().set(ReportWeek::getWorkHour, workHour).eq(ReportWeek::getId, reportWeekDto.getId()));
        reportWeekDetailsService.saveBatch(reportWeekDto.getReportWeekDetailsList());
        return result;
    }

}
