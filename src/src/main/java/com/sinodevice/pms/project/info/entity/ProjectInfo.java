package com.sinodevice.pms.project.info.entity;

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
@ApiModel(value = "ProjectInfo对象", description = "")
public class ProjectInfo extends BaseEntity {

    @ApiModelProperty(value = "项目名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "项目代号")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "项目负责人")
    @TableField("RESPONSER")
    private Integer responser;

    @ApiModelProperty(value = "备注信息")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "状态 0:未开始 1:进行中 2:已完成 3:已关闭 ")
    private Integer status;
}
