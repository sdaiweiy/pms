package com.sinodevice.pms.project.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcess;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ProjectTaskProcessDto extends ProjectTaskProcess {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;


}
