package com.sinodevice.pms.project.task.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.task.dto.ProjectTaskProcessDto;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcess;
import com.sinodevice.pms.project.task.service.IProjectTaskProcessService;
import com.sinodevice.pms.project.task.vo.ProjectTaskProcessVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/project/task/process")
public class ProjectTaskProcessController extends BaseController<IProjectTaskProcessService, ProjectTaskProcess> {

    @GetMapping("/getByTaskId")
    public R<ProjectTaskProcess> AAA(ProjectTaskProcess projectTaskProcess) {
        Account account = LoginHelper.getAccount();
        return R.ok(this.baseService.getOne(Wrappers.<ProjectTaskProcess>lambdaQuery()
                .eq(ProjectTaskProcess::getUserId, account.getId())
                .eq(ProjectTaskProcess::getTaskId, projectTaskProcess.getTaskId())
        ));
    }

    @PostMapping("/start")
    public R<Boolean> startTask(ProjectTaskProcessDto projectTaskProcessDto) {
        return this.success(this.baseService.startTask(projectTaskProcessDto.getTaskId(), projectTaskProcessDto.getTime()));
    }

    @PostMapping("/pause")
    public R<Boolean> pauseTask(ProjectTaskProcessDto projectTaskProcessDto) {
        return this.success(this.baseService.pauseTask(projectTaskProcessDto.getTaskId(), projectTaskProcessDto.getTime()));
    }

    @PostMapping("/continue")
    public R<Boolean> continueTask(ProjectTaskProcessDto projectTaskProcessDto) {
        return this.success(this.baseService.continueTask(projectTaskProcessDto.getTaskId(), projectTaskProcessDto.getTime()));
    }

    @PostMapping("/finish")
    public R<Boolean> finishTask(ProjectTaskProcess projectTaskProcess) {
        return this.success(this.baseService.finishTask(projectTaskProcess));
    }

    @GetMapping("/listLoginUserWeekTask")
    public R<List<ProjectTaskProcessVo>> listLoginUserWeekTask() {
        return R.ok(this.baseService.listLoginUserWeekTask());
    }

    @GetMapping("/getTaskWorkHour")
    public R<Map> getTaskWorkHour(Long taskId) {
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime", this.baseService.getLastBeginTime(taskId));
        map.put("workHour", this.baseService.getTaskWorkHour(taskId));
        return R.ok(map);
    }

}
