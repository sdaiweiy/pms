package com.sinodevice.pms.project.info.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.project.info.dto.ProjectInfoDto;
import com.sinodevice.pms.project.info.entity.ProjectInfo;
import com.sinodevice.pms.project.info.vo.ProjectInfoVo;
import com.sinodevice.pms.project.info.vo.ProjectStatusVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
public interface IProjectInfoService extends IService<ProjectInfo> {

    IPage<ProjectInfoVo> page(IPage<ProjectInfoVo> page, ProjectInfo projectInfo);

    List<ProjectInfo> listByUserId(Long type);

    List<ProjectStatusVo> listProjectStatus();

    Boolean saveDto(ProjectInfoDto projectInfoDto);

    Boolean updateDto(ProjectInfoDto projectInfoDto);

}
