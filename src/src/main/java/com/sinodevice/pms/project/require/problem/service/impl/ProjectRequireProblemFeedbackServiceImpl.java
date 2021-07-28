package com.sinodevice.pms.project.require.problem.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.project.require.document.service.IProjectRequireDocumentService;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblem;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblemFeedback;
import com.sinodevice.pms.project.require.problem.mapper.ProjectRequireProblemFeedbackMapper;
import com.sinodevice.pms.project.require.problem.service.IProjectRequireProblemFeedbackService;
import com.sinodevice.pms.project.require.problem.service.IProjectRequireProblemService;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemFeedbackVo;
import com.sinodevice.pms.project.user.entity.ProjectInfoUser;
import com.sinodevice.pms.project.user.service.IProjectInfoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 需求文档反馈 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Service
public class ProjectRequireProblemFeedbackServiceImpl extends ServiceImpl<ProjectRequireProblemFeedbackMapper, ProjectRequireProblemFeedback> implements IProjectRequireProblemFeedbackService {

    @Autowired
    private IProjectRequireProblemService projectRequireProblemService;

    @Autowired
    private IProjectRequireDocumentService projectRequireDocumentService;

    @Autowired
    private IProjectInfoUserService projectInfoUserService;

    @Override
    public List<ProjectRequireProblemFeedbackVo> list(Long problemId) {
        return this.baseMapper.list(problemId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProjectRequireProblemFeedback projectRequireProblemFeedback) {
        Account account = LoginHelper.getAccount();
        projectRequireProblemFeedback.setCreateBy(account.getId());
        projectRequireProblemFeedback.setCreateTime(LocalDateTime.now());

        ProjectRequireProblem projectRequireProblem = projectRequireProblemService.getById(projectRequireProblemFeedback.getProblemId());
        Long projectId = projectRequireDocumentService.getById(projectRequireProblem.getDocumentId()).getProjectId();

        ProjectInfoUser projectInfoUser = projectInfoUserService.getOne(Wrappers.<ProjectInfoUser>lambdaQuery().eq(ProjectInfoUser::getProjectId, projectId).eq(ProjectInfoUser::getUserId, account.getId()));
        ProjectInfoUser createProjectInfoUser = projectInfoUserService.getOne(Wrappers.<ProjectInfoUser>lambdaQuery().eq(ProjectInfoUser::getProjectId, projectId).eq(ProjectInfoUser::getUserId, projectRequireProblem.getCreateBy()));

        if (createProjectInfoUser.getType() == projectInfoUser.getType()) {
            if (projectRequireProblem.getStatus() != 0) {
                projectRequireProblem.setStatus(2);
            }
        } else {
            projectRequireProblem.setStatus(1);
        }
        projectRequireProblemService.updateById(projectRequireProblem);
        return super.save(projectRequireProblemFeedback);
    }
}
