package com.sinodevice.pms.report.daily.vo;

import com.sinodevice.pms.report.daily.entity.ReportDaily;
import lombok.Data;

/**
 * @author ZF
 * @create 2021/7/28 14:29
 */
@Data
public class ReportDailyPageVo extends ReportDaily {

    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 创建人姓名
     */
    private String createByName;

}
