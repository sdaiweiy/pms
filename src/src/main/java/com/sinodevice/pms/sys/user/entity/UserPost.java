package com.sinodevice.pms.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户角色关联表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_post")
public class UserPost extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    @ApiModelProperty(value = "岗位 ID")
    private Long postId;

}
