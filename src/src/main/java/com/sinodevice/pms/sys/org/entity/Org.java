package com.sinodevice.pms.sys.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统组织表
 * </p>
 *
 * @author jobob
 * @since 2018-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_org")
public class Org extends BaseEntity {

    @ApiModelProperty(value = "父 ID")
    private Long pid;

    @ApiModelProperty(value = "根 ID")
    private Long rid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "code别名")
    @TableField(exist = false)
    private String description;

    @ApiModelProperty(value = "id别名")
    @TableField(exist = false)
    private Long value;

}
