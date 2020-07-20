package com.sinodevice.pms.sys.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通用数据字典
 * </p>
 *
 * @author wdai
 * @since 2019-05-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "DictCommon对象", description = "通用数据字典 ")
public class DictCommon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父节点")
    @TableField("PID")
    private Long pid;

    @ApiModelProperty(value = "字典名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "字典对应的数据表")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "数据类型 0:列表1:树")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

}
