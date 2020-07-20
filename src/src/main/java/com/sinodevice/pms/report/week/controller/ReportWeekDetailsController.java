package com.sinodevice.pms.report.week.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.week.service.IReportWeekDetailsService;
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
@RequestMapping("/report/week/details")
public class ReportWeekDetailsController extends BaseController<IReportWeekDetailsService, ReportWeekDetails> {

    @GetMapping("/listByReportId")
    public R<List<ReportWeekDetails>> getByReportId(String reportId) {
        return R.ok(this.baseService.list(Wrappers.<ReportWeekDetails>lambdaQuery().eq(ReportWeekDetails::getReportId, reportId)));
    }

    @GetMapping("/list")
    public R<List<ReportWeekDetailsVo>> list(Long weekIndex) {
        return success(baseService.list(weekIndex));
    }

}
