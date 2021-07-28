package com.sinodevice.pms.project.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.project.info.entity.ProjectInfo;
import com.sinodevice.pms.project.info.service.IProjectInfoService;
import com.sinodevice.pms.project.test.dto.ProjectTestProblemDto;
import com.sinodevice.pms.project.test.entity.ProjectTestProblem;
import com.sinodevice.pms.project.test.mapper.ProjectTestProblemMapper;
import com.sinodevice.pms.project.test.service.IProjectTestProblemService;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 功能测试反馈 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
@Service
public class ProjectTestProblemServiceImpl extends ServiceImpl<ProjectTestProblemMapper, ProjectTestProblem> implements IProjectTestProblemService {

    @Autowired
    private IProjectInfoService projectInfoService;

    @Override
    public IPage<ProjectTestProblemVo> page(IPage<ProjectTestProblemVo> page, ProjectTestProblemDto projectTestProblemDto) {
        List<Long> enableProjectIdList = projectInfoService.listByUserId(null).stream().map(ProjectInfo::getId).collect(Collectors.toList());
        projectTestProblemDto.setProjectList(enableProjectIdList);
        if (enableProjectIdList.size() > 0) {
            return page.setRecords(this.baseMapper.page(page, projectTestProblemDto));
        }
        return page.setRecords(new ArrayList<>());
    }
}
