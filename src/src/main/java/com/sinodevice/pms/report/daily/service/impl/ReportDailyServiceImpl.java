package com.sinodevice.pms.report.daily.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public IPage<ReportDailyPageVo> page(IPage<ReportDailyPageVo> page, ReportDailyPageDto dto) {
        dto.setLoginUserId(LoginHelper.getAccount().getId());
        return this.baseMapper.page(page, dto);
    }

    @Override
    public ReportDailyVo getOneById(Long id) {
        ReportDailyVo vo = this.baseMapper.getOneById(id);
        vo.setDetails(reportDailyDetailsService.listByDailyId(vo.getId()));
        return vo;
    }

    @Override
    public Boolean saveDto(ReportDailyDto dto) {
        dto.setDate(LocalDate.now());
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
    public List<ReportDaily> has() {
        return this.list(Wrappers.<ReportDaily>lambdaQuery()
                .eq(ReportDaily::getDate, LocalDate.now())
                .eq(ReportDaily::getCreateBy, LoginHelper.getAccount().getId()));
    }
}
