package com.sinodevice.pms.report.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.work.vo.ReportWorkWeekVo;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportWorkService extends IService<ReportWeekDetails> {

    List<ReportWorkWeekVo> list(LocalDateTime beginTime, LocalDateTime endTime);

}
