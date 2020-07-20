package com.sinodevice.pms.sys.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.sys.resource.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统资源表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-09-13
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {


    /**
     * <p>
     * 查询用户菜单权限
     * </p>
     *
     * @param userId 用户 ID
     * @return
     */
    List<Resource> selectMenuByUserId(@Param("userId") Long userId);

    /**
     * <p>
     * 根据 id 查询所有子节点
     * </p>
     *
     * @param id ID
     * @return
     */
    List<Resource> queryChildrenIds(Serializable id);
}
