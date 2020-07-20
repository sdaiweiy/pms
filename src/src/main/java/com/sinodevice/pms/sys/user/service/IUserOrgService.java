package com.sinodevice.pms.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.user.entity.UserOrg;
import com.sinodevice.pms.sys.user.vo.UserOrgSelectedVO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户组织关联表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */
public interface IUserOrgService extends IService<UserOrg> {


    /**
     * <p>
     * 查询用户所属组织 VO
     * </p>
     *
     * @param userId 用户 ID
     * @return
     */
    List<UserOrgSelectedVO> listSelectedVO(Long userId);


    /**
     * <p>
     * 更新用户组织关联信息
     * </p>
     *
     * @param userId 用户 ID
     * @param ids    组织 ID 集合
     * @return
     */
    boolean updateByIds(Long userId, List<Long> ids);


    /**
     * <p>
     * 删除用户组织关系
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
     * @param orgId 组织 ID
     * @return
     */
    boolean relation(Serializable orgId);
}
