package com.sinodevice.pms.report.daily.controller;


import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.report.daily.entity.ReportDailyDetails;
import com.sinodevice.pms.report.daily.service.IReportDailyDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 日报 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
@RestController
@RequestMapping("/report/daily/details")
public class ReportDailyDetailsController extends BaseController<IReportDailyDetailsService, ReportDailyDetails> {

}
