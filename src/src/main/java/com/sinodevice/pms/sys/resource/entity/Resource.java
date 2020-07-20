package com.sinodevice.pms.sys.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.common.utils.Pinyin4jUtils;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统资源表
 * </p>
 *
 * @author jobob
 * @since 2018-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_resource")
public class Resource extends BaseEntity {

    @ApiModelProperty(value = "父 ID")
    private Long pid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "首字母")
    private String initial;

    @ApiModelProperty(value = "类型 0、菜单 1、按钮")
    private Integer type;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "URI")
    private String uri;

    @ApiModelProperty(value = "文件路径")
    private String path;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "状态  -1、禁用 0、正常")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;


    public Resource initialName() {
        if (null != name) {
            this.initial = Pinyin4jUtils.converterToAllFirstSpell(name);
        }
        return this;
    }
}
