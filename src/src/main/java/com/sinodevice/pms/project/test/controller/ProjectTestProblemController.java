package com.sinodevice.pms.project.test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.test.dto.ProjectTestProblemDto;
import com.sinodevice.pms.project.test.entity.ProjectTestProblem;
import com.sinodevice.pms.project.test.service.IProjectTestProblemService;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 功能测试反馈 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/project/test/problem")
public class ProjectTestProblemController extends BaseController<IProjectTestProblemService, ProjectTestProblem> {

    @GetMapping("/page")
    public R<IPage<ProjectTestProblemVo>> page(ProjectTestProblemDto projectTestProblemDto) {
        return success(baseService.page(getPage(), projectTestProblemDto));
    }

}
