package com.sinodevice.pms.report.week.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.report.week.dto.ReportWeekDto;
import com.sinodevice.pms.report.week.entity.ReportWeek;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
public interface IReportWeekService extends IService<ReportWeek> {

    IPage<ReportWeek> page(IPage<ReportWeek> page, ReportWeek reportWeek);

    IPage<ReportWeek> pageTotal(IPage<ReportWeek> page, ReportWeek reportWeek);

    Boolean saveDto(ReportWeekDto reportWeekDto);

}
