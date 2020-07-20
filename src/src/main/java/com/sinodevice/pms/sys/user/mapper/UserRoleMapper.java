package com.sinodevice.pms.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.sys.user.entity.UserRole;
import com.sinodevice.pms.sys.user.vo.UserRoleSelectedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户角色关联表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {


    /**
     * <p>
     * 根据用户 ID 查询用户选择角色 VO
     * </p>
     *
     * @param userId 用户 ID
     * @return
     */
    List<UserRoleSelectedVO> selectSelectedVO(@Param("userId") Long userId);
}
