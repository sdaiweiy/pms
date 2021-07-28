package com.sinodevice.pms.project.require.problem.vo;

import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblemFeedback;
import lombok.Data;

/**
 * <p>
 * 需求文档反馈
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Data
public class ProjectRequireProblemFeedbackVo extends ProjectRequireProblemFeedback {

    private String createByName;

    //人员类型
    private int userType;

}
