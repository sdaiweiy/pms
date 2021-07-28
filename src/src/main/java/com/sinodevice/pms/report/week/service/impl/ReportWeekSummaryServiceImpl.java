package com.sinodevice.pms.report.week.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.report.week.entity.ReportWeekSummary;
import com.sinodevice.pms.report.week.mapper.ReportWeekSummaryMapper;
import com.sinodevice.pms.report.week.service.IReportWeekSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
                .like(summary.getPlan() != null, ReportWeekSummary::getPlan, summary.getPlan())
                .orderByDesc(ReportWeekSummary::getBeginDate));
    }

    @Override
    public boolean save(ReportWeekSummary reportWeekSummary) {
        checkReportDateRange(reportWeekSummary);
        return super.save(reportWeekSummary);
    }

    @Override
    public boolean updateById(ReportWeekSummary reportWeekSummary) {
        checkReportDateRange(reportWeekSummary);
        return super.updateById(reportWeekSummary);
    }

    private void checkReportDateRange(ReportWeekSummary reportWeekSummary) {
        LocalDate beginDate = reportWeekSummary.getBeginDate();
        LocalDate endDate = reportWeekSummary.getEndDate();

        if (null == reportWeekSummary.getId()) {
            List<ReportWeekSummary> reportWeekSummaryList = this.list(Wrappers.<ReportWeekSummary>lambdaQuery().between(ReportWeekSummary::getBeginDate, beginDate, endDate)
                    .or().between(ReportWeekSummary::getEndDate, beginDate, endDate));

            Assert.fail(reportWeekSummaryList.size() > 0, "部门周报日期重叠,请修改开始或结束日期!");
        } else {
            List<ReportWeekSummary> reportWeekSummaryList = this.list(Wrappers.<ReportWeekSummary>lambdaQuery().ne(ReportWeekSummary::getId, reportWeekSummary.getId())
                    .and(obj -> obj.between(ReportWeekSummary::getBeginDate, beginDate, endDate)
                            .or().between(ReportWeekSummary::getEndDate, beginDate, endDate)));

            Assert.fail(reportWeekSummaryList.size() > 0, "部门周报日期重叠,请修改开始或结束日期!");
        }
    }
}
