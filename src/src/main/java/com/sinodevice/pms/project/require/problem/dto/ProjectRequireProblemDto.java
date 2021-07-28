package com.sinodevice.pms.project.require.problem.dto;

import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblem;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 需求文档疑问
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Data
public class ProjectRequireProblemDto extends ProjectRequireProblem {

    private Long projectId;

    private String beginTime;

    private String endTime;

    private List<Long> projectList;

}
