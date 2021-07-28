package com.sinodevice.pms.project.test.vo;

import com.sinodevice.pms.project.test.entity.ProjectTestProblemFeedback;
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
public class ProjectTestProblemFeedbackVo extends ProjectTestProblemFeedback {

    private String createByName;

    //人员类型
    private int userType;

}
