package com.sinodevice.pms.project.require.document.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目需求文档-提纲
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ProjectRequireDocument对象", description = "项目需求文档-提纲")
public class ProjectRequireDocument extends BaseEntity {

    @ApiModelProperty(value = "父节点编号")
    @TableField("PID")
    private Long pid;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "文档节点标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "排序号")
    @TableField("SORT")
    private Integer sort;

    @ApiModelProperty(value = "备注信息")
    @TableField("REMARK")
    private String remark;

}
