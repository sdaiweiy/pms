package com.sinodevice.pms.project.test.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.project.require.document.service.IProjectRequireDocumentService;
import com.sinodevice.pms.project.test.entity.ProjectTestProblem;
import com.sinodevice.pms.project.test.entity.ProjectTestProblemFeedback;
import com.sinodevice.pms.project.test.mapper.ProjectTestProblemFeedbackMapper;
import com.sinodevice.pms.project.test.service.IProjectTestProblemFeedbackService;
import com.sinodevice.pms.project.test.service.IProjectTestProblemService;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemFeedbackVo;
import com.sinodevice.pms.project.user.entity.ProjectInfoUser;
import com.sinodevice.pms.project.user.service.IProjectInfoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 功能测试反馈 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
@Service
public class ProjectTestProblemFeedbackServiceImpl extends ServiceImpl<ProjectTestProblemFeedbackMapper, ProjectTestProblemFeedback> implements IProjectTestProblemFeedbackService {

    @Autowired
    private IProjectTestProblemService projectTestProblemService;

    @Autowired
    private IProjectRequireDocumentService projectRequireDocumentService;

    @Autowired
    private IProjectInfoUserService projectInfoUserService;

    @Override
    public List<ProjectTestProblemFeedbackVo> list(Long problemId) {
        return this.baseMapper.list(problemId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProjectTestProblemFeedback projectTestProblemFeedback) {
        Account account = LoginHelper.getAccount();
        projectTestProblemFeedback.setCreateBy(account.getId());
        projectTestProblemFeedback.setCreateTime(LocalDateTime.now());

        ProjectTestProblem projectTestProblem = projectTestProblemService.getById(projectTestProblemFeedback.getProblemId());
        Long projectId = projectRequireDocumentService.getById(projectTestProblem.getDocumentId()).getProjectId();

        ProjectInfoUser currentUser = projectInfoUserService.getOne(Wrappers.<ProjectInfoUser>lambdaQuery().eq(ProjectInfoUser::getProjectId, projectId).eq(ProjectInfoUser::getUserId, account.getId()));

        if (0 == currentUser.getType()) {
            if (projectTestProblem.getStatus() != 0) {
                projectTestProblem.setStatus(2);
            }
        } else {
            projectTestProblem.setStatus(1);
        }
        projectTestProblemService.updateById(projectTestProblem);
        return super.save(projectTestProblemFeedback);
    }
}
