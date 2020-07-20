package com.sinodevice.pms.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.user.entity.UserRole;
import com.sinodevice.pms.sys.user.vo.UserRoleSelectedVO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户角色关联表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */
public interface IUserRoleService extends IService<UserRole> {


    /**
     * <p>
     * 查询用户选择角色 VO
     * </p>
     *
     * @param userId 用户 ID
     * @return
     */
    List<UserRoleSelectedVO> listSelectedVO(Long userId);


    /**
     * <p>
     * 更新用户角色关联信息
     * </p>
     *
     * @param userId 用户 ID
     * @param ids    角色 ID 集合
     * @return
     */
    boolean updateByIds(Long userId, List<Long> ids);


    /**
     * <p>
     * 删除用户角色关系
     * </p>
     *
     * @param userId 用户 ID
     * @return
     */
    boolean removeByUserId(Serializable userId);

    /**
     * <p>
     * 是否存在与用户关联
     * </p>
     *
     * @param roleId 角色 ID
     * @return
     */
    boolean relation(Serializable roleId);

}
