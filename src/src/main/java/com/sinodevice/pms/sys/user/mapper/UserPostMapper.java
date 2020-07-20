package com.sinodevice.pms.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.sys.user.entity.UserPost;
import com.sinodevice.pms.sys.user.vo.UserPostVo;
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
public interface UserPostMapper extends BaseMapper<UserPost> {

    /**
     * <p>
     * 根据用户 ID 查询用户选择岗位 VO
     * </p>
     */
    List<UserPostVo> listPostByUserId(@Param("userId") Long userId);

    int existPost(@Param("userId") Long userId, @Param("postCode") String postCode);

}
