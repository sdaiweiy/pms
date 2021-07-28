package com.sinodevice.pms.project.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.project.test.entity.ProjectTestProblemFeedback;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemFeedbackVo;

import java.util.List;

/**
 * <p>
 * 功能测试反馈 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
public interface IProjectTestProblemFeedbackService extends IService<ProjectTestProblemFeedback> {

    List<ProjectTestProblemFeedbackVo> list(Long problemId);

}
