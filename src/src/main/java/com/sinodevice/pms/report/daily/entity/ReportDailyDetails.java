package com.sinodevice.pms.report.daily.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * <p>
 * 日报
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReportDailyDetails对象", description = "日报")
public class ReportDailyDetails extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日报编号")
    @TableField("DAILY_ID")
    private Long dailyId;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_ID")
    private Integer projectId;

    @ApiModelProperty(value = "工作内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "需求调研时间")
    @TableField("REQUIRE_TIME")
    private BigDecimal requireTime;

    @ApiModelProperty(value = "协助开发时间")
    @TableField("DEV_TIME")
    private BigDecimal devTime;

    @ApiModelProperty(value = "测试上线时间")
    @TableField("TEST_TIME")
    private BigDecimal testTime;

    @ApiModelProperty(value = "实施运维时间")
    @TableField("OP_TIME")
    private BigDecimal opTime;

    @ApiModelProperty(value = "总时长")
    @TableField("TOTAL_TIME")
    private BigDecimal totalTime;

}
