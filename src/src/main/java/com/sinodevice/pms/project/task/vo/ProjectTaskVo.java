package com.sinodevice.pms.project.task.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.project.task.entity.ProjectTask;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ProjectTaskVo extends ProjectTask {

    private String projectName;

    private String moduleName;

    private String createByName;

    private int workHourSum;

    private String taskUsers;

    private String taskUnFinishUsers = "";

    private String taskUnStartUsers = "";

    private String taskPauseUsers = "";

    private String taskUsersName;

    private String taskRemark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime taskBeginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime taskEndTime;

}
