package com.sinodevice.pms.report.week.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ReportWeek对象", description = "")
public class ReportWeek extends BaseEntity {

    @ApiModelProperty(value = "用户编号")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "开始时间")
    @TableField("BEGIN_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @ApiModelProperty(value = "结束时间")
    @TableField("END_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ApiModelProperty(value = "周报备注信息")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "下周计划")
    @TableField("PLAN")
    private String plan;

    @ApiModelProperty(value = "协调/解决的问题")
    @TableField("RESOLVE")
    private String resolve;

    @ApiModelProperty(value = "第几周周报")
    @TableField("WEEK_INDEX")
    private Integer weekIndex;

    @ApiModelProperty(value = "本周工作时长")
    @TableField("WORK_HOUR")
    private BigDecimal workHour;

}
