package com.sinodevice.pms.report.work.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReportWorkWeekVo {

    private String userRealName;

    private List<ReportWorkDailyVo> reportWorkDailyVoList;

    private BigDecimal weekWorkHour;

}
