package com.sinodevice.pms.report.daily.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sinodevice.pms.report.daily.entity.ReportDaily;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

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

    @ApiModelProperty(value = "本日工作总时长")
    @TableField("DAY_TOTAL_TIME")
    private BigDecimal dayTotalTime;

}
