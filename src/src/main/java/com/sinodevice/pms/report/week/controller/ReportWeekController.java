package com.sinodevice.pms.report.week.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.report.week.dto.ReportWeekDto;
import com.sinodevice.pms.report.week.entity.ReportWeek;
import com.sinodevice.pms.report.week.service.IReportWeekService;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
@RestController
@RequestMapping("/report/week")
public class ReportWeekController extends BaseController<IReportWeekService, ReportWeek> {

    @GetMapping("/page")
    public R<IPage<ReportWeek>> page(ReportWeek reportWeek) {
        if (LoginHelper.getAccount().getId() != 10001) {
            reportWeek.setUserId(LoginHelper.getAccount().getId());
        }
        return success(baseService.page(getPage(), reportWeek));
    }

    @GetMapping("/list")
    public R<IPage<ReportWeek>> list(ReportWeek reportWeek) {
        return success(baseService.page(getPage(), reportWeek));
    }

    @GetMapping("/pageTotal")
    public R<IPage<ReportWeek>> pageTotal(ReportWeek reportWeek) {
        return success(baseService.pageTotal(getPage(), reportWeek));
    }

    @GetMapping("/weekList")
    public R<IPage<ReportWeek>> weekList(ReportWeek reportWeek) {
        return success(baseService.weekList(getPage(), reportWeek));
    }

    @PostMapping("/dto")
    public R<Boolean> add(@RequestBody ReportWeekDto reportWeekDto) {
        return R.ok(baseService.saveDto(reportWeekDto));
    }
}
