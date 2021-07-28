package com.sinodevice.pms.project.require.problem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.project.info.entity.ProjectInfo;
import com.sinodevice.pms.project.info.service.IProjectInfoService;
import com.sinodevice.pms.project.require.problem.dto.ProjectRequireProblemDto;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblem;
import com.sinodevice.pms.project.require.problem.mapper.ProjectRequireProblemMapper;
import com.sinodevice.pms.project.require.problem.service.IProjectRequireProblemService;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 需求文档疑问 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Service
public class ProjectRequireProblemServiceImpl extends ServiceImpl<ProjectRequireProblemMapper, ProjectRequireProblem> implements IProjectRequireProblemService {

    @Autowired
    private IProjectInfoService projectInfoService;

    @Override
    public IPage<ProjectRequireProblemVo> page(IPage<ProjectRequireProblemVo> page, ProjectRequireProblemDto projectRequireProblemDto) {
        List<Long> enableProjectIdList = projectInfoService.listByUserId(null).stream().map(ProjectInfo::getId).collect(Collectors.toList());
        projectRequireProblemDto.setProjectList(enableProjectIdList);
        if (enableProjectIdList.size() > 0) {
            return page.setRecords(this.baseMapper.page(page, projectRequireProblemDto));
        }
        return page.setRecords(new ArrayList<>());
    }

}
