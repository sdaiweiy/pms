package com.sinodevice.pms.report.work.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.work.service.IReportWorkService;
import com.sinodevice.pms.report.work.vo.ReportWorkWeekVo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
@RequestMapping("/report/work")
public class ReportWorkController extends BaseController<IReportWorkService, ReportWeekDetails> {

    @GetMapping("/list")
    public R<List<ReportWorkWeekVo>> list(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime beginTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(baseService.list(beginTime, endTime));
    }

}
