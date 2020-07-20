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
 * 列表型字典
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dict_common_plain")
@ApiModel(value = "DictCommonPlain对象", description = "列表型字典 ")
public class DictCommonPlain extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典代码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "字典名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "拼音缩写")
    @TableField("INITIAL")
    private String initial;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "状态 0、正常 1、禁用")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    public DictCommonPlain initialName() {
        if (null != name) {
            this.initial = Pinyin4jUtils.converterToAllFirstSpell(name);
        }
        return this;
    }
}
