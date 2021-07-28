package com.sinodevice.pms.project.test.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemFeedbackVo;
import com.sinodevice.pms.project.test.entity.ProjectTestProblemFeedback;
import com.sinodevice.pms.project.test.service.IProjectTestProblemFeedbackService;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemFeedbackVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 功能测试反馈 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/project/test/problem/feedback")
public class ProjectTestProblemFeedbackController extends BaseController<IProjectTestProblemFeedbackService, ProjectTestProblemFeedback> {

    @GetMapping("/list")
    public R<List<ProjectTestProblemFeedbackVo>> list(Long problemId) {
        return success(this.baseService.list(problemId));
    }

}
