package com.sinodevice.pms.report.work.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReportWorkDailyVo {

    private List<ReportWorkDetailsVo> reportWorkDetailsVoList;

    private BigDecimal dailyWorkHour;
}
