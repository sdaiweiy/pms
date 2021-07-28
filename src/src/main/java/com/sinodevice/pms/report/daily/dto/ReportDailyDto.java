package com.sinodevice.pms.report.daily.dto;

import com.sinodevice.pms.report.daily.entity.ReportDaily;
import com.sinodevice.pms.report.daily.entity.ReportDailyDetails;
import lombok.Data;

import java.util.List;

/**
 * @author ZF
 * @create 2021/7/28 23:01
 */
@Data
public class ReportDailyDto extends ReportDaily {

    /**
     * 项目详情
     */
    private List<ReportDailyDetails> details;
}
