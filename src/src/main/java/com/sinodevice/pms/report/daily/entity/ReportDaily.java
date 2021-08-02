package com.sinodevice.pms.report.daily.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@ApiModel(value = "ReportDaily对象", description = "日报")
public class ReportDaily extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableField("USER_ID")
    private Integer userId;

    @ApiModelProperty(value = "日报时间")
    @TableField("DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ApiModelProperty(value = "总需求调研时间")
    @TableField("TOTAL_REQUIRE_TIME")
    private BigDecimal totalRequireTime;

    @ApiModelProperty(value = "总协助开发时间")
    @TableField("TOTAL_DEV_TIME")
    private BigDecimal totalDevTime;

    @ApiModelProperty(value = "总测试上线时间")
    @TableField("TOTAL_TEST_TIME")
    private BigDecimal totalTestTime;

    @ApiModelProperty(value = "总实施运维时间")
    @TableField("TOTAL_OP_TIME")
    private BigDecimal totalOpTime;

    @ApiModelProperty(value = "总其它时间")
    @TableField("TOTAL_OTHER_TIME")
    private BigDecimal totalOtherTime;

    @ApiModelProperty(value = "总时长")
    @TableField("TOTAL_TIME")
    private BigDecimal totalTime;

    @ApiModelProperty(value = "总加班时长")
    @TableField("TOTAL_OVER_TIME")
    private BigDecimal totalOverTime;

    @ApiModelProperty(value = "其他事项说明")
    @TableField("DESCRIPTION")
    private String description;

}
