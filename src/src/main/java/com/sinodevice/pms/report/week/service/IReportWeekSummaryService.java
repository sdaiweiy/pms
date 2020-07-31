package com.sinodevice.pms.report.week.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.report.week.entity.ReportWeek;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.week.entity.ReportWeekSummary;
import com.sinodevice.pms.report.week.vo.ReportWeekDetailsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
public interface IReportWeekSummaryService extends IService<ReportWeekSummary> {

    IPage<ReportWeekSummary> page(IPage<ReportWeekSummary> page, ReportWeekSummary summary);

}
