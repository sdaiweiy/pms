package com.sinodevice.pms.sys.user.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户权限 DTO
 * </p>
 *
 * @author jobob
 * @since 2018-10-05
 */
@Data
@Accessors(chain = true)
public class UserResourceDTO {
    private long userId;
    private long resourceId;
}
