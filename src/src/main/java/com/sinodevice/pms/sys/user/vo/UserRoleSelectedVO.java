package com.sinodevice.pms.sys.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户选择角色 VO
 * </p>
 *
 * @author jobob
 * @since 2018-10-09
 */
@Data
public class UserRoleSelectedVO implements Serializable {

    /**
     * 角色 ID
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 选择
     */
    private Boolean selected;
    /**
     * 角色首页
     */
    private String indexPage;

}
