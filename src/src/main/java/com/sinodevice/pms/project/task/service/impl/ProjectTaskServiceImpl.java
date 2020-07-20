package com.sinodevice.pms.project.task.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.project.task.dto.ProjectTaskDto;
import com.sinodevice.pms.project.task.entity.ProjectTask;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcess;
import com.sinodevice.pms.project.task.mapper.ProjectTaskMapper;
import com.sinodevice.pms.project.task.service.IProjectTaskProcessService;
import com.sinodevice.pms.project.task.service.IProjectTaskService;
import com.sinodevice.pms.project.task.vo.ProjectTaskVo;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ProjectTaskServiceImpl extends ServiceImpl<ProjectTaskMapper, ProjectTask> implements IProjectTaskService {

    @Autowired
    private IProjectTaskProcessService projectTaskProcessService;

    @Autowired
    private IUserPostService userPostService;

    @Override
    public IPage<ProjectTaskVo> page(IPage<ProjectTaskVo> page, ProjectTaskDto projectTaskDto) {
        Account account = LoginHelper.getAccount();

        if (userPostService.existPost(account.getId(), "BMFZ")) {
            projectTaskDto.setShowAll(true);
        }
        return page.setRecords(this.baseMapper.page(page, projectTaskDto, account.getId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveDto(ProjectTaskDto projectTaskDto) {
        boolean result = super.save(projectTaskDto);

        String[] taskUsers = projectTaskDto.getTaskUsers().split(",");
        for (String taskUser : taskUsers) {
            ProjectTaskProcess projectTaskProcess = new ProjectTaskProcess();
            projectTaskProcess.setTaskId(projectTaskDto.getId());
            projectTaskProcess.setUserId(Integer.valueOf(taskUser));
            projectTaskProcessService.save(projectTaskProcess);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editDto(ProjectTaskDto projectTaskDto) {
        boolean result = super.updateById(projectTaskDto);
        String[] taskUsers = projectTaskDto.getTaskUsers().split(",");
        List<ProjectTaskProcess> projectTaskProcessList = projectTaskProcessService.list(
                Wrappers.<ProjectTaskProcess>lambdaQuery().eq(ProjectTaskProcess::getTaskId, projectTaskDto.getId()));

        for (ProjectTaskProcess projectTaskProcess : projectTaskProcessList) {
            boolean find = false;
            for (String taskUser : taskUsers) {
                if (projectTaskProcess.getUserId().equals(taskUser)) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                //如果这个人没有进行任务操作,则删除这个人
                if (projectTaskProcess.getBeginTime() == null) {
                    projectTaskProcessService.removeById(projectTaskProcess.getId());
                }
            }
        }

        for (String taskUser : taskUsers) {
            boolean find = false;
            for (ProjectTaskProcess projectTaskProcess : projectTaskProcessList) {
                if (projectTaskProcess.getUserId().equals(taskUser)) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                ProjectTaskProcess newProjectTaskProcess = new ProjectTaskProcess();
                newProjectTaskProcess.setTaskId(projectTaskDto.getId());
                newProjectTaskProcess.setUserId(Integer.valueOf(taskUser));
                projectTaskProcessService.save(newProjectTaskProcess);
            }
        }

        return result;
    }

}
