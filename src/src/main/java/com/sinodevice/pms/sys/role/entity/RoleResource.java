package com.sinodevice.pms.sys.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色资源关联表
 * </p>
 *
 * @author jobob
 * @since 2018-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_resource")
public class RoleResource extends SuperEntity {

    @ApiModelProperty(value = "角色 ID")
    private Long roleId;

    @ApiModelProperty(value = "资源 ID")
    private Long resourceId;

}
