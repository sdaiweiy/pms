package com.sinodevice.pms.sys.param.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.common.utils.Pinyin4jUtils;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统参数表
 * </p>
 *
 * @author jobob
 * @since 2018-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_param")
public class Param extends BaseEntity {

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "首字母")
    private String initial;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "系统参数 0、否 1、是")
    private Integer sys;

    @ApiModelProperty(value = "备注")
    private String remark;


    public Param initialName() {
        if (null != name) {
            this.initial = Pinyin4jUtils.converterToAllFirstSpell(name);
        }
        return this;
    }
}
