package com.sinodevice.pms.report.daily.vo;

import com.sinodevice.pms.report.daily.entity.ReportDailyDetails;
import lombok.Data;

/**
 * @author ZF
 * @create 2021/7/28 16:35
 */
@Data
public class ReportDailyDetailsVo extends ReportDailyDetails {

    /**
     * 项目名称
     */
    private String projectName;
}
