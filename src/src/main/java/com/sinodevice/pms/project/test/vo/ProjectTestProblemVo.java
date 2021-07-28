package com.sinodevice.pms.project.test.vo;

import com.sinodevice.pms.project.test.entity.ProjectTestProblem;
import lombok.Data;

@Data
public class ProjectTestProblemVo extends ProjectTestProblem {

    private String projectName;

    private String documentName;

    private String createByName;

}
