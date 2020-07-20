package com.sinodevice.pms.report.week.entity;

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
 * @since 2020-02-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("report_week_details")
@ApiModel(value = "ReportWeekDetails对象", description = "")
public class ReportWeekDetails extends SuperEntity {

    @ApiModelProperty(value = "日报编号")
    @TableField("REPORT_ID")
    private Long reportId;

    @ApiModelProperty(value = "开始时间")
    @TableField("BEGIN_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "关联任务编号")
    @TableField("TASK_ID")
    private Integer taskId;

    @ApiModelProperty(value = "任务名称")
    @TableField("TASK_NAME")
    private String taskName;

    @ApiModelProperty(value = "任务状态")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "0:任务关联 1:自行添加")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "任务消耗时间")
    @TableField("WORK_HOUR")
    private BigDecimal workHour;

}
