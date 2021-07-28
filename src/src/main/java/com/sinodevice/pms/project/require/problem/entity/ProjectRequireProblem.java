package com.sinodevice.pms.project.require.problem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 需求文档疑问
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ProjectRequireProblem对象", description = "需求文档疑问")
public class ProjectRequireProblem extends BaseEntity {

    @ApiModelProperty(value = "问题关联的需求文档大纲")
    @TableField("DOCUMENT_ID")
    private Integer documentId;

    @ApiModelProperty(value = "当前问题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "问题描述")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "0:待处理 1:已反馈 2:待继续处理 3:已关闭")
    @TableField("STATUS")
    private Integer status;

}
