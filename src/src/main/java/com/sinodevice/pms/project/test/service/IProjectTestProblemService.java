package com.sinodevice.pms.project.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.project.test.dto.ProjectTestProblemDto;
import com.sinodevice.pms.project.test.entity.ProjectTestProblem;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemVo;

/**
 * <p>
 * 功能测试反馈 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
public interface IProjectTestProblemService extends IService<ProjectTestProblem> {

    IPage<ProjectTestProblemVo> page(IPage<ProjectTestProblemVo> page, ProjectTestProblemDto projectTestProblemDto);

}
