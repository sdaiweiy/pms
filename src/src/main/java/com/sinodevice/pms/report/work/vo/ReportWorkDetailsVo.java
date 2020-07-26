package com.sinodevice.pms.report.work.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReportWorkDetailsVo {

    private Long taskId;

    private String projectName;

    private String taskName;

    private String userRealName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime realEndTime;

    private BigDecimal workHour;

    private String day;

}
