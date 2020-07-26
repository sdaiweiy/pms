package com.sinodevice.pms.project.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 任务完成整体过程记录
 * </p>
 *
 * @author wdai
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("project_task_process_details")
@ApiModel(value = "ProjectTaskProcessDetails对象", description = "任务完成整体过程记录")
public class ProjectTaskProcessDetails extends SuperEntity {

    @ApiModelProperty(value = "任务ID")
    @TableField("TASK_ID")
    private Long taskId;

    @ApiModelProperty(value = "完成人编号")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "开始时间")
    @TableField("BEGIN_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "完成时间")
    @TableField("END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "任务消耗时间")
    @TableField("WORK_HOUR")
    private BigDecimal workHour;

    @ApiModelProperty(value = "实际开始时间")
    @TableField("REAL_BEGIN_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime realBeginTime;

    @ApiModelProperty(value = "实际结束时间")
    @TableField("REAL_END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime realEndTime;

}
