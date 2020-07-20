package com.sinodevice.pms.report.week.dto;

import com.sinodevice.pms.report.week.entity.ReportWeek;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class ReportWeekDto extends ReportWeek {

    @Autowired
    private List<ReportWeekDetails> reportWeekDetailsList;

}
