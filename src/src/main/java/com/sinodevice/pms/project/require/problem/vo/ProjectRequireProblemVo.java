package com.sinodevice.pms.project.require.problem.vo;

import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblem;
import lombok.Data;

@Data
public class ProjectRequireProblemVo extends ProjectRequireProblem {

    private String projectName;

    private String documentName;

    private String createByName;

}
