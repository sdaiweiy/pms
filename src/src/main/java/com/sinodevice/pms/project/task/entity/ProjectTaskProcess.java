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
 *
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("project_task_process")
@ApiModel(value = "ProjectTaskProcess对象", description = "")
public class ProjectTaskProcess extends SuperEntity {

    @ApiModelProperty(value = "任务编号")
    @TableField("TASK_ID")
    private Long taskId;

    @ApiModelProperty(value = "完成人编号")
    @TableField("USER_ID")
    private Integer userId;

    @ApiModelProperty(value = "备注信息")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "任务开始时间")
    @TableField("BEGIN_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "任务结束时间")
    @TableField("END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "任务消耗时间")
    @TableField("WORK_HOUR")
    private BigDecimal workHour;

    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private Integer status;
}
