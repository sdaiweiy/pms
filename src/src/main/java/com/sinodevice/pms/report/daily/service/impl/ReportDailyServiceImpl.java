package com.sinodevice.pms.report.daily.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.report.daily.dto.ReportDailyDto;
import com.sinodevice.pms.report.daily.dto.ReportDailyPageDto;
import com.sinodevice.pms.report.daily.entity.ReportDaily;
import com.sinodevice.pms.report.daily.entity.ReportDailyDetails;
import com.sinodevice.pms.report.daily.mapper.ReportDailyMapper;
import com.sinodevice.pms.report.daily.service.IReportDailyDetailsService;
import com.sinodevice.pms.report.daily.service.IReportDailyService;
import com.sinodevice.pms.report.daily.vo.ReportDailyPageVo;
import com.sinodevice.pms.report.daily.vo.ReportDailyVo;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 日报 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
@Service
public class ReportDailyServiceImpl extends ServiceImpl<ReportDailyMapper, ReportDaily> implements IReportDailyService {

    @Autowired
    private IReportDailyDetailsService reportDailyDetailsService;
    @Autowired
    private IUserPostService userPostService;

    @Override
    public IPage<ReportDailyPageVo> page(IPage<ReportDailyPageVo> page, ReportDailyPageDto dto) {
        beforeDto(dto);
        return this.baseMapper.page(page, dto);
    }

    @Override
    public ReportDailyPageVo statistics(ReportDailyPageDto dto) {
        beforeDto(dto);
        BigDecimal totalRequireTime = new BigDecimal("0");
        BigDecimal totalDevTime = new BigDecimal("0");
        BigDecimal totalTestTime = new BigDecimal("0");
        BigDecimal totalOpTime = new BigDecimal("0");
        BigDecimal totalOtherTime = new BigDecimal("0");
        BigDecimal totalTime = new BigDecimal("0");
        BigDecimal totalOverTime = new BigDecimal("0");
        BigDecimal totalCancelTime = new BigDecimal("0");

        List<ReportDailyPageVo> reportDailyPageVos = this.baseMapper.list(dto);
        for (ReportDailyPageVo dailyPageVo : reportDailyPageVos) {
            totalRequireTime = totalRequireTime.add(dailyPageVo.getTotalRequireTime());
            totalDevTime = totalDevTime.add(dailyPageVo.getTotalDevTime());
            totalTestTime = totalTestTime.add(dailyPageVo.getTotalTestTime());
            totalOpTime = totalOpTime.add(dailyPageVo.getTotalOpTime());
            totalOtherTime = totalOtherTime.add(dailyPageVo.getTotalOtherTime());
            totalTime = totalTime.add(dailyPageVo.getTotalTime());
            totalOverTime = totalOverTime.add(dailyPageVo.getTotalOverTime());
            if (null != dailyPageVo.getCancelTime()) {
                totalCancelTime = totalCancelTime.add(dailyPageVo.getCancelTime());
            }
        }

        ReportDailyPageVo reportDailyPageVo = new ReportDailyPageVo();
        reportDailyPageVo.setTotalRequireTime(totalRequireTime);
        reportDailyPageVo.setTotalDevTime(totalDevTime);
        reportDailyPageVo.setTotalTestTime(totalTestTime);
        reportDailyPageVo.setTotalOpTime(totalOpTime);
        reportDailyPageVo.setTotalOtherTime(totalOtherTime);
        reportDailyPageVo.setTotalTime(totalTime);
        reportDailyPageVo.setTotalOverTime(totalOverTime);
        reportDailyPageVo.setCancelTime(totalCancelTime);
        return reportDailyPageVo;
    }

    private void beforeDto(ReportDailyPageDto dto) {
        if (!userPostService.existPost(LoginHelper.getAccount().getId(), "BMFZ")) {
            dto.setCreateBy(LoginHelper.getAccount().getId());
        }
    }

    @Override
    public ReportDailyVo getOneById(Long id) {
        ReportDailyVo vo = this.baseMapper.getOneById(id);
        vo.setDetails(reportDailyDetailsService.listByDailyId(vo.getId()));
        return vo;
    }

    @Override
    public Boolean saveDto(ReportDailyDto dto) {
        List<ReportDaily> reportDailies = this.list(Wrappers.<ReportDaily>lambdaQuery()
                .eq(ReportDaily::getDate, dto.getDate())
                .eq(ReportDaily::getCreateBy, LoginHelper.getAccount().getId()));
        Assert.fail(reportDailies.size() != 0, "今日日报已存在，请勿重复提交");
        this.save(dto);
        dto.getDetails().forEach(d -> d.setDailyId(dto.getId()));
        reportDailyDetailsService.saveBatch(dto.getDetails());
        return true;
    }

    @Override
    public Boolean updateDto(ReportDailyDto dto) {
        this.updateById(dto);
        reportDailyDetailsService.remove(Wrappers.<ReportDailyDetails>lambdaQuery()
                .eq(ReportDailyDetails::getDailyId, dto.getId()));
        dto.getDetails().forEach(d -> d.setDailyId(dto.getId()));
        reportDailyDetailsService.saveBatch(dto.getDetails());
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean cancel(String[] dailyIdList, LocalDate cancelDay) {
        List<ReportDaily> reportDailyList = new ArrayList<>();

        for (String dailyId : dailyIdList) {
            ReportDaily reportDaily = new ReportDaily();
            ReportDaily temp = this.getById(Long.parseLong(dailyId));
            reportDaily.setId(Long.parseLong(dailyId));
            reportDaily.setCancelDay(cancelDay);
            reportDaily.setCancelTime(temp.getTotalOverTime());
            reportDailyList.add(reportDaily);
        }
        return this.updateBatchById(reportDailyList);
    }

}
