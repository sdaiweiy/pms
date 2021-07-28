package com.sinodevice.pms.project.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 功能测试反馈
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("project_test_problem_feedback")
@ApiModel(value = "ProjectTestProblemFeedback对象", description = "功能测试反馈")
public class ProjectTestProblemFeedback extends SuperEntity {

    @ApiModelProperty(value = "问题ID")
    @TableField("PROBLEM_ID")
    private Long problemId;

    @ApiModelProperty(value = "反馈内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_BY")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

}
