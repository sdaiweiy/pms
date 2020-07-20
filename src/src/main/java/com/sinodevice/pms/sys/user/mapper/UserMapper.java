package com.sinodevice.pms.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinodevice.pms.sys.user.dto.UserDTO;
import com.sinodevice.pms.sys.user.dto.UserInfoDTO;
import com.sinodevice.pms.sys.user.dto.UserResourceDTO;
import com.sinodevice.pms.sys.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * <p>
     * 分页查询用户
     * </p>
     *
     * @return
     */
    List<User> queryUser(IPage<User> page, @Param(value = "user") User user);

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
    List<UserResourceDTO> queryByUserIdAndResourceId(@Param(value = "userId") long userId, @Param(value = "resourceId") long resourceId);

    /**
     * 查询个人信息
     * @param id
     * @return
     */
    List<UserInfoDTO> queryUserInfo(@Param(value = "id") long id);
}
