package com.sinodevice.pms.report.week.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.report.week.entity.ReportWeekSummary;
import com.sinodevice.pms.report.week.mapper.ReportWeekSummaryMapper;
import com.sinodevice.pms.report.week.service.IReportWeekSummaryService;
import org.springframework.stereotype.Service;

/**
 * @author ZF
 * @create 2020/7/30 20:54
 */
@Service
public class ReportWeekSummaryServiceImpl extends ServiceImpl<ReportWeekSummaryMapper, ReportWeekSummary> implements IReportWeekSummaryService {
    @Override
    public IPage<ReportWeekSummary> page(IPage<ReportWeekSummary> page, ReportWeekSummary summary) {
        return super.page(page, Wrappers.<ReportWeekSummary>lambdaQuery()
                .le(summary.getEndDate() != null, ReportWeekSummary::getEndDate, summary.getEndDate())
                .ge(summary.getBeginDate() != null, ReportWeekSummary::getBeginDate, summary.getBeginDate())
                .like(summary.getContent() != null, ReportWeekSummary::getContent, summary.getContent())
                .like(summary.getPlan() != null, ReportWeekSummary::getPlan, summary.getPlan()));
    }
}
