package com.sinodevice.pms.project.require.problem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblemFeedback;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemFeedbackVo;

import java.util.List;

/**
 * <p>
 * 需求文档反馈 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
public interface IProjectRequireProblemFeedbackService extends IService<ProjectRequireProblemFeedback> {

    List<ProjectRequireProblemFeedbackVo> list(Long problemId);

}
