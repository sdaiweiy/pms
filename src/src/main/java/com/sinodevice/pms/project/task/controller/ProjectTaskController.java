package com.sinodevice.pms.project.task.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.task.dto.ProjectTaskDto;
import com.sinodevice.pms.project.task.entity.ProjectTask;
import com.sinodevice.pms.project.task.service.IProjectTaskService;
import com.sinodevice.pms.project.task.vo.ProjectTaskVo;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/project/task")
public class ProjectTaskController extends BaseController<IProjectTaskService, ProjectTask> {

    @GetMapping("/page")
    public R<IPage<ProjectTaskVo>> page(ProjectTaskDto projectTaskDto) {
        return success(baseService.page(getPage(), projectTaskDto));
    }

    @PostMapping("/dto")
    public R<Boolean> add(ProjectTaskDto projectTaskDto) {
        return this.success(this.baseService.saveDto(projectTaskDto));
    }

    @PutMapping("/dto")
    public R<Boolean> edit(ProjectTaskDto projectTaskDto) {
        return this.success(this.baseService.editDto(projectTaskDto));
    }

}
