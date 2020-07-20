package com.sinodevice.pms.project.task.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.project.task.entity.ProjectTask;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcess;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcessDetails;
import com.sinodevice.pms.project.task.mapper.ProjectTaskProcessMapper;
import com.sinodevice.pms.project.task.service.IProjectTaskProcessDetailsService;
import com.sinodevice.pms.project.task.service.IProjectTaskProcessService;
import com.sinodevice.pms.project.task.service.IProjectTaskService;
import com.sinodevice.pms.project.task.vo.ProjectTaskProcessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
public class ProjectTaskProcessServiceImpl extends ServiceImpl<ProjectTaskProcessMapper, ProjectTaskProcess> implements IProjectTaskProcessService {

    @Autowired
    @Lazy
    private IProjectTaskService projectTaskService;

    @Autowired
    private IProjectTaskProcessDetailsService projectTaskProcessDetailsService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean startTask(Long taskId, LocalDateTime time) {
        Account account = LoginHelper.getAccount(false);

        projectTaskService.update(Wrappers.<ProjectTask>lambdaUpdate()
                .set(ProjectTask::getStatus, 1).set(ProjectTask::getRealBeginTime, time)
                .eq(ProjectTask::getId, taskId).eq(ProjectTask::getStatus, 0));

        //保存处理过程
        ProjectTaskProcessDetails projectTaskProcessDetails = new ProjectTaskProcessDetails();
        projectTaskProcessDetails.setBeginTime(time);
        projectTaskProcessDetails.setTaskId(taskId);
        projectTaskProcessDetails.setUserId(account.getId());
        projectTaskProcessDetailsService.save(projectTaskProcessDetails);

        return this.update(Wrappers.<ProjectTaskProcess>lambdaUpdate()
                .set(ProjectTaskProcess::getBeginTime, time)
                .eq(ProjectTaskProcess::getTaskId, taskId)
                .eq(ProjectTaskProcess::getUserId, account.getId())
        );
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean finishTask(ProjectTaskProcess projectTaskProcess) {
        projectTaskProcess.setStatus(1);
        boolean result = this.updateById(projectTaskProcess);

        //保存处理过程
        updateTaskProcessEndTimeDetails(projectTaskProcess.getTaskId(), projectTaskProcess.getEndTime(), 1);

        int count = this.count(Wrappers.<ProjectTaskProcess>lambdaQuery()
                .eq(ProjectTaskProcess::getTaskId, projectTaskProcess.getTaskId())
                .eq(ProjectTaskProcess::getStatus, 0));

        //所有人都完成了任务
        if (count == 0) {
            projectTaskService.update(Wrappers.<ProjectTask>lambdaUpdate()
                    .set(ProjectTask::getStatus, 2).set(ProjectTask::getRealEndTime, LocalDateTime.now())
                    .eq(ProjectTask::getId, projectTaskProcess.getTaskId()));
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean pauseTask(Long taskId, LocalDateTime time) {
        return updateTaskProcessEndTimeDetails(taskId, time, 2);
    }

    private Boolean updateTaskProcessEndTimeDetails(Long taskId, LocalDateTime endTime, int taskStatus) {
        Account account = LoginHelper.getAccount(false);

        ProjectTaskProcessDetails projectTaskProcessDetails = projectTaskProcessDetailsService.getOne(Wrappers.<ProjectTaskProcessDetails>lambdaQuery()
                .eq(ProjectTaskProcessDetails::getTaskId, taskId)
                .eq(ProjectTaskProcessDetails::getUserId, account.getId())
                .isNotNull(ProjectTaskProcessDetails::getBeginTime)
                .isNull(ProjectTaskProcessDetails::getEndTime));
        projectTaskProcessDetails.setEndTime(endTime);

        Duration duration = Duration.between(projectTaskProcessDetails.getBeginTime(), projectTaskProcessDetails.getEndTime());
        projectTaskProcessDetails.setWorkHour(BigDecimal.valueOf(duration.toMinutes() / 60.0).setScale(1, BigDecimal.ROUND_UP));

        this.update(Wrappers.<ProjectTaskProcess>lambdaUpdate()
                .set(ProjectTaskProcess::getStatus, taskStatus)
                .eq(ProjectTaskProcess::getTaskId, taskId)
                .eq(ProjectTaskProcess::getUserId, account.getId()));

        return projectTaskProcessDetailsService.updateById(projectTaskProcessDetails);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean continueTask(Long taskId, LocalDateTime time) {
        Account account = LoginHelper.getAccount(false);

        //保存处理过程
        ProjectTaskProcessDetails projectTaskProcessDetails = new ProjectTaskProcessDetails();
        projectTaskProcessDetails.setBeginTime(time);
        projectTaskProcessDetails.setTaskId(taskId);
        projectTaskProcessDetails.setUserId(account.getId());

        this.update(Wrappers.<ProjectTaskProcess>lambdaUpdate()
                .set(ProjectTaskProcess::getStatus, 0)
                .eq(ProjectTaskProcess::getTaskId, taskId)
                .eq(ProjectTaskProcess::getUserId, account.getId()));

        return projectTaskProcessDetailsService.save(projectTaskProcessDetails);
    }

    @Override
    public List<ProjectTaskProcessVo> listLoginUserWeekTask() {
        Account account = LoginHelper.getAccount();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate today = LocalDate.now();
        String beginTime = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).format(fmt) + " 00:00:00";
        String endTime = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).format(fmt) + " 23:59:59";
        return this.baseMapper.listThisWeekTask(account.getId(), beginTime, endTime);
    }

    @Override
    public BigDecimal getTaskWorkHour(Long taskId) {
        Account account = LoginHelper.getAccount(false);
        return this.baseMapper.getTaskWorkHour(account.getId(), taskId);
    }

    @Override
    public LocalDateTime getLastBeginTime(Long taskId) {
        Account account = LoginHelper.getAccount(false);
        return this.baseMapper.getLastBeginTime(account.getId(), taskId);
    }

}
