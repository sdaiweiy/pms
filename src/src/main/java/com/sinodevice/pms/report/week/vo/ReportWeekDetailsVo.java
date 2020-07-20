package com.sinodevice.pms.report.week.vo;

import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import lombok.Data;

@Data
public class ReportWeekDetailsVo extends ReportWeekDetails {

    private String realName;

    private String taskRemark;

}
