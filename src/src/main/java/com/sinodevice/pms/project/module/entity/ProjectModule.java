package com.sinodevice.pms.project.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ProjectModule对象", description = "")
public class ProjectModule extends BaseEntity {

    @ApiModelProperty(value = "父节点编号")
    @TableField("PID")
    private Integer pid;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_ID")
    private Integer projectId;

    @ApiModelProperty(value = "模块编号")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

}
