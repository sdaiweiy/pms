package com.sinodevice.pms.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户组织关联表
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_org")
public class UserOrg extends SuperEntity {

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    @ApiModelProperty(value = "组织 ID")
    private Long orgId;

}
