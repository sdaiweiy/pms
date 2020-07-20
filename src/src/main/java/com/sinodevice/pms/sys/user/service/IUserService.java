package com.sinodevice.pms.sys.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.login.dto.LoginDTO;
import com.sinodevice.pms.sys.user.dto.ResetPswDTO;
import com.sinodevice.pms.sys.user.dto.UserDTO;
import com.sinodevice.pms.sys.user.dto.UserInfoDTO;
import com.sinodevice.pms.sys.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */
public interface IUserService extends IService<User> {

       /**
     * <p>
     * 根据用户信息分页
     * </p>
     *
     * @param page 分页对象
     * @param user 用户信息
     * @return
     */
    IPage<User> queryUser(IPage<User> page, User user);


    /**
     * <p>
     * 查询用户账号是否已存在
     * </p>
     * @param id 用户 ID
     * @param username 用户账号
     * @return
     */
    int userNameCount(Long id,String username);


    /**
     * <p>
     * 保存用户角色关系信息
     * </p>
     *
     * @param dto 用户 DTO
     * @return
     */
    boolean saveDto(UserDTO dto);



    /**
     * <p>
     * 修改用户角色关系信息
     * </p>
     *
     * @param dto 用户 DTO
     * @return
     */
    boolean updateDtoById(UserDTO dto);


    /**
     * <p>
     * 登录设置 COOKIE
     * </p>
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @param dto      用户 DTO
     * @return
     */
    User loginByDto(HttpServletRequest request, HttpServletResponse response, LoginDTO dto);


    /**
     * <p>
     * 更新用户状态
     * </p>
     *
     * @param id     用户 ID
     * @param status 状态
     * @return
     */
    boolean updateStatus(Long id, Integer status);


    /**
     * <p>
     * 解锁用户
     * </p>
     *
     * @param id       用户 ID
     * @param password 明文密码
     * @return
     */
    boolean unlock(Long id, String password);


    /**
     * <p>
     * 重置指定 ID 用户的登录密码
     * </p>
     *
     * @param id 用户 ID
     * @return
     */
    boolean resetPassword(Long id);

    /**
     * <p>
     * 修改 登录密码
     * </p>
     *
     * @param dto
     * @return
     */
    boolean resetPswOwn(ResetPswDTO dto);

    /**
     * 查询所有角色为接待处的人员，带组织名称
     * @return
     */
    List<UserDTO> listReceptionUser();

    /**
     * 查询用户权限
     * @param userId
     * @param resourceId
     * @return
     */
    boolean queryByUserIdAndResourceId(long userId,long resourceId);

    /**
     * 查询个人信息
     * @param id
     * @return
     */
    UserInfoDTO queryUserInfo(long id);

}
