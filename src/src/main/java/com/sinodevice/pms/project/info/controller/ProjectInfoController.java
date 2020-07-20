package com.sinodevice.pms.project.info.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.common.ErrorCode;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.info.dto.ProjectInfoDto;
import com.sinodevice.pms.project.info.entity.ProjectInfo;
import com.sinodevice.pms.project.info.service.IProjectInfoService;
import com.sinodevice.pms.project.info.vo.ProjectInfoVo;
import com.sinodevice.pms.project.info.vo.ProjectStatusVo;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/project/info")
public class ProjectInfoController extends BaseController<IProjectInfoService, ProjectInfo> {

    @GetMapping("/page")
    public R<IPage<ProjectInfoVo>> page(ProjectInfo projectInfo) {
        return success(baseService.page(getPage(), projectInfo));
    }

    @GetMapping("/list")
    public R<List<ProjectInfo>> list() {
        return success(baseService.list());
    }

    @GetMapping("/listUserId")
    public R<List<ProjectInfo>> listUserId() {
        return success(baseService.listByUserId());
    }

    @GetMapping("/listProjectStatus")
    public R<List<ProjectStatusVo>> listProjectStatus() {
        return success(baseService.listProjectStatus());
    }

    @PostMapping("/dto")
    public R<Boolean> addDto(ProjectInfoDto projectInfoDto) {
        return this.success(this.baseService.saveDto(projectInfoDto));
    }

    @PutMapping("/dto")
    public R<Boolean> editDto(ProjectInfoDto projectInfoDto) {
        Assert.fail(null == projectInfoDto.getId(), ErrorCode.ID_REQUIRED);
        return this.success(this.baseService.updateDto(projectInfoDto));
    }
}
