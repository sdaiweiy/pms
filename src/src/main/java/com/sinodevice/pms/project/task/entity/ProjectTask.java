package com.sinodevice.pms.project.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ProjectTask对象", description = "")
public class ProjectTask extends BaseEntity {

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_ID")
    private Integer projectId;

    @ApiModelProperty(value = "模块编号")
    @TableField("MODULE_ID")
    private Integer moduleId;

    @ApiModelProperty(value = "任务类型")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "任务名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "任务描述")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "计划开始时间")
    @TableField("PLAN_BEGIN_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate planBeginDate;

    @ApiModelProperty(value = "计划结束时间")
    @TableField("PLAN_END_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate planEndDate;

    @ApiModelProperty(value = "计划用时")
    @TableField("PLAN_HOUR")
    private Integer planHour;

    @ApiModelProperty(value = "任务实际开始时间")
    @TableField("REAL_BEGIN_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime realBeginTime;

    @ApiModelProperty(value = "任务实际完成时间")
    @TableField("REAL_END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime realEndTime;

    @ApiModelProperty(value = "任务取消时间")
    @TableField("CANCEL_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime cancelTime;

    @ApiModelProperty(value = "任务取消人")
    @TableField("CANCEL_USER")
    private Integer cancelUser;

    @ApiModelProperty(value = "任务关闭时间")
    @TableField("CLOSE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime closeTime;

    @ApiModelProperty(value = "任务关闭人")
    @TableField("CLOSE_USER")
    private Integer closeUser;

    @ApiModelProperty(value = "关闭原因:0:已完成 1:已取消")
    @TableField("CLOSE_REASON")
    private Integer closeReason;

    @ApiModelProperty(value = "任务优先级 1:紧急,2:重要,3:次要,4:琐事")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "任务优先级 1,2,3,4,5")
    @TableField("LEVEL")
    private Integer level;

}
