package com.sinodevice.pms.project.module.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.module.entity.ProjectModule;
import com.sinodevice.pms.project.module.service.IProjectModuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/project/module")
public class ProjectModuleController extends BaseController<IProjectModuleService, ProjectModule> {

    @GetMapping("/list")
    public R<List<ProjectModule>> page(ProjectModule projectModule) {
        return success(baseService.list(Wrappers.<ProjectModule>lambdaQuery().eq(ProjectModule::getProjectId, projectModule.getProjectId()).orderByAsc(ProjectModule::getSort)));
    }

}
