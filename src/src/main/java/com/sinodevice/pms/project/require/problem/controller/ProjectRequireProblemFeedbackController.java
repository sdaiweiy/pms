package com.sinodevice.pms.project.require.problem.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblemFeedback;
import com.sinodevice.pms.project.require.problem.service.IProjectRequireProblemFeedbackService;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemFeedbackVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 需求文档反馈 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@RestController
@RequestMapping("/project/require/problem/feedback")
public class ProjectRequireProblemFeedbackController extends BaseController<IProjectRequireProblemFeedbackService, ProjectRequireProblemFeedback> {

    @GetMapping("/list")
    public R<List<ProjectRequireProblemFeedbackVo>> list(Long problemId) {
        return success(this.baseService.list(problemId));
    }

}
