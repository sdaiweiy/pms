package com.sinodevice.pms.project.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinodevice.pms.project.test.dto.ProjectTestProblemDto;
import com.sinodevice.pms.project.test.entity.ProjectTestProblem;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 功能测试反馈 Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
@Mapper
public interface ProjectTestProblemMapper extends BaseMapper<ProjectTestProblem> {

    List<ProjectTestProblemVo> page(IPage<ProjectTestProblemVo> page, @Param(value = "projectTestProblem") ProjectTestProblemDto projectTestProblemDto);

}
