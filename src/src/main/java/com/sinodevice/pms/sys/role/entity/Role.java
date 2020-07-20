package com.sinodevice.pms.sys.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.common.utils.Pinyin4jUtils;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author jobob
 * @since 2018-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends BaseEntity {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "首页")
    private String indexPage;

    @ApiModelProperty(value = "首字母")
    private String initial;

    @ApiModelProperty(value = "状态  -1、禁用 0、正常")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;


    public Role initialName() {
        if (null != name) {
            this.initial = Pinyin4jUtils.converterToAllFirstSpell(name);
        }
        return this;
    }
}
