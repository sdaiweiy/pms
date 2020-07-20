package com.sinodevice.pms.project.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.project.task.dto.ProjectTaskDto;
import com.sinodevice.pms.project.task.entity.ProjectTask;
import com.sinodevice.pms.project.task.vo.ProjectTaskVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
public interface IProjectTaskService extends IService<ProjectTask> {

    IPage<ProjectTaskVo> page(IPage<ProjectTaskVo> page, ProjectTaskDto projectTaskDto);

    Boolean saveDto(ProjectTaskDto projectTaskDto);

    Boolean editDto(ProjectTaskDto projectTaskDto);

}
