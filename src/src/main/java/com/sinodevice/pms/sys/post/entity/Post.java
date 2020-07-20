package com.sinodevice.pms.sys.post.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.common.utils.Pinyin4jUtils;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统岗位表
 * </p>
 *
 * @author jobob
 * @since 2018-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_post")
public class Post extends BaseEntity {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "首字母")
    private String initial;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "状态  0、正常 1、禁用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;


    public Post initialName() {
        if (null != name) {
            this.initial = Pinyin4jUtils.converterToAllFirstSpell(name);
        }
        return this;
    }
}
