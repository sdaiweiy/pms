package com.sinodevice.pms.project.info.vo;

import com.sinodevice.pms.project.info.entity.ProjectInfo;
import lombok.Data;

@Data
public class ProjectInfoVo extends ProjectInfo {

    private String responserName;

    private String userList;

    private String developerList;

}
