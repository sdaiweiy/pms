package com.sinodevice.pms.project.require.document.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.require.document.entity.ProjectRequireDocument;
import com.sinodevice.pms.project.require.document.service.IProjectRequireDocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 项目需求文档-提纲 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@RestController
@RequestMapping("/project/require/document")
public class ProjectRequireDocumentController extends BaseController<IProjectRequireDocumentService, ProjectRequireDocument> {

    @GetMapping("/list")
    public R<List<ProjectRequireDocument>> page(ProjectRequireDocument projectRequireDocument) {
        return success(baseService.list(Wrappers.<ProjectRequireDocument>lambdaQuery().eq(ProjectRequireDocument::getProjectId, projectRequireDocument.getProjectId()).orderByAsc(ProjectRequireDocument::getSort)));
    }

}
