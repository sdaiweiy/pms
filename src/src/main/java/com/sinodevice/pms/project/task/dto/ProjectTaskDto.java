package com.sinodevice.pms.project.task.dto;

import com.sinodevice.pms.project.task.entity.ProjectTask;
import lombok.Data;

@Data
public class ProjectTaskDto extends ProjectTask {

    private String taskUsers;

    private boolean showAll;

}
