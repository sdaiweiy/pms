package com.sinodevice.pms.report.week.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.report.week.entity.ReportWeek;
import com.sinodevice.pms.report.week.entity.ReportWeekSummary;
import com.sinodevice.pms.report.week.service.IReportWeekSummaryService;
import com.sinodevice.pms.report.week.vo.ReportWeekDetailsVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
