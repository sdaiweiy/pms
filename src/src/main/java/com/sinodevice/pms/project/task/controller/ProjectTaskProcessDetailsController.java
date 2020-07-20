package com.sinodevice.pms.project.task.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.task.service.IProjectTaskProcessDetailsService;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcessDetails;


/**
 * <p>
 * 任务完成整体过程记录 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/task/project-task-process-details")
public class ProjectTaskProcessDetailsController extends BaseController<IProjectTaskProcessDetailsService, ProjectTaskProcessDetails> {

}
