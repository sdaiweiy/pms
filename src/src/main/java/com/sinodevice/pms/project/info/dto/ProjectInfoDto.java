package com.sinodevice.pms.project.info.dto;

import com.sinodevice.pms.project.info.entity.ProjectInfo;
import lombok.Data;

@Data
public class ProjectInfoDto extends ProjectInfo {

    private String userList;

    private String developerList;

}
