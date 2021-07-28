package com.sinodevice.pms.project.test.dto;

import com.sinodevice.pms.project.test.entity.ProjectTestProblem;
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
public class ProjectTestProblemDto extends ProjectTestProblem {

    private Long projectId;

    private String beginTime;

    private String endTime;

    private List<Long> projectList;

}
