package com.sinodevice.pms.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "随机盐")
    private String salt;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "状态 -1、冻结 0、正常")
    private Integer status;

    @ApiModelProperty(value = "所属角色 用于搜索")
    @TableField(exist=false)
    private String queryRoleIds;

    @ApiModelProperty(value = "角色名称 用于展示")
    @TableField(exist=false)
    private String showRoles;

    @ApiModelProperty(value = "角色首页 用于跳转")
    @TableField(exist=false)
    private String indexPage;

    @ApiModelProperty(value = "组织名称 用于展示")
    @TableField(exist=false)
    private String showOrgs;

    @ApiModelProperty(value = "岗位名称 用于展示")
    @TableField(exist=false)
    private String showPosts;

    @ApiModelProperty(value = "每日工作时长")
    private BigDecimal workTime;
}
