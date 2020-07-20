package com.sinodevice.pms.sys.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.common.utils.Pinyin4jUtils;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通用-树键值对字典
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dict_common_tree")
@ApiModel(value = "DictCommonTree对象", description = "通用-树键值对字典 ")
public class DictCommonTree extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父节点")
    @TableField("PID")
    private Long pid;

    @ApiModelProperty(value = "字典代码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "本字典代码")
    @TableField("NODE_CODE")
    private String nodeCode;

    @ApiModelProperty(value = "字典名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "拼音缩写")
    @TableField("INITIAL")
    private String initial;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "状态 0:正常1:禁用")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    public DictCommonTree initialName() {
        if (null != name) {
            this.initial = Pinyin4jUtils.converterToAllFirstSpell(name);
        }
        return this;
    }
}
