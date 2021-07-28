package com.sinodevice.pms.project.require.problem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.project.require.problem.dto.ProjectRequireProblemDto;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblem;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemVo;

/**
 * <p>
 * 需求文档疑问 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
public interface IProjectRequireProblemService extends IService<ProjectRequireProblem> {

    IPage<ProjectRequireProblemVo> page(IPage<ProjectRequireProblemVo> page, ProjectRequireProblemDto projectRequireProblemDto);

}
