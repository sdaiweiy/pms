package com.sinodevice.pms.project.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.project.module.entity.ProjectModule;
import com.sinodevice.pms.project.module.mapper.ProjectModuleMapper;
import com.sinodevice.pms.project.module.service.IProjectModuleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@Service
public class ProjectModuleServiceImpl extends ServiceImpl<ProjectModuleMapper, ProjectModule> implements IProjectModuleService {

    @Override
    public boolean removeById(Serializable id) {
        // Assert.fail(roleResourceService.relation(id), "存在关联任务操作无效");
        List<ProjectModule> childrenIds = this.baseMapper.queryChildrenIds(id);
        ArrayList<Serializable> ids = new ArrayList<Serializable>();
        for (ProjectModule childrenId : childrenIds) {
            ids.add(childrenId.getId());
        }
        return super.removeByIds(ids);
    }

}
