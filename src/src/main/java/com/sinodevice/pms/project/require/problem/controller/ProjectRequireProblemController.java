package com.sinodevice.pms.project.require.problem.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.require.problem.dto.ProjectRequireProblemDto;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblem;
import com.sinodevice.pms.project.require.problem.service.IProjectRequireProblemService;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 需求文档疑问 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@RestController
@RequestMapping("/project/require/problem")
public class ProjectRequireProblemController extends BaseController<IProjectRequireProblemService, ProjectRequireProblem> {

    @GetMapping("/page")
    public R<IPage<ProjectRequireProblemVo>> page(ProjectRequireProblemDto projectRequireProblemDto) {
        return success(baseService.page(getPage(), projectRequireProblemDto));
    }

}
