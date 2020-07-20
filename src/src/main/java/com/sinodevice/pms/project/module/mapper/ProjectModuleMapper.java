package com.sinodevice.pms.project.module.mapper;

import com.sinodevice.pms.project.module.entity.ProjectModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.sys.resource.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@Mapper
public interface ProjectModuleMapper extends BaseMapper<ProjectModule> {

    /**
     * <p>
     * 根据 id 查询所有子节点
     * </p>
     *
     * @param id ID
     * @return
     */
    List<ProjectModule> queryChildrenIds(Serializable id);

}
