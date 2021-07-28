package com.sinodevice.pms.report.week.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.report.week.entity.ReportWeekSummary;
import com.sinodevice.pms.report.week.service.IReportWeekSummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
@RestController
@RequestMapping("/report/week/summary")
public class ReportWeekSummaryController extends BaseController<IReportWeekSummaryService, ReportWeekSummary> {

    @GetMapping("/page")
    public R<IPage<ReportWeekSummary>> page(ReportWeekSummary summary) {
        return success(baseService.page(getPage(), summary));
    }

    @GetMapping("/getWeekSummary")
    public R<ReportWeekSummary> getWeekSummary(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<ReportWeekSummary> reportWeekSummaryList = baseService.list(Wrappers.<ReportWeekSummary>lambdaQuery().between(ReportWeekSummary::getBeginDate, beginDate, endDate)
                .or().between(ReportWeekSummary::getEndDate, beginDate, endDate));
        if (reportWeekSummaryList.size() > 0) {
            return success(reportWeekSummaryList.get(0));
        }
        return success(null);
    }

}
