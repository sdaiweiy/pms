package com.sinodevice.pms.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.user.entity.UserPost;
import com.sinodevice.pms.sys.user.vo.UserPostVo;

import java.io.Serializable;
import java.util.List;

public interface IUserPostService extends IService<UserPost> {

    /**
     * <p>
     * 查询用户选择角色 VO
     * </p>
     *
     * @param userId 用户 ID
     * @return
     */
    List<UserPostVo> listPostByUserId(Long userId);

    /**
     * <p>
     * 删除用户岗位关系
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
     * @param postId 组织 ID
     * @return
     */
    boolean relation(Serializable postId);

    /**
     * <p>
     * 更新用户岗位关联信息
     * </p>
     *
     * @param userId 用户 ID
     * @param ids    岗位 ID 集合
     * @return
     */
    boolean updateByIds(Long userId, List<Long> ids);

    /***
     * 查询用户是否有对应的岗位权限
     * @param userId
     * @param postCode
     * @return
     */
    boolean existPost(Long userId, String postCode);

}
