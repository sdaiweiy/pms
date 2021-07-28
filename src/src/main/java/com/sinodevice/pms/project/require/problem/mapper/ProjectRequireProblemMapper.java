package com.sinodevice.pms.project.require.problem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinodevice.pms.project.require.problem.dto.ProjectRequireProblemDto;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblem;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 需求文档疑问 Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Mapper
public interface ProjectRequireProblemMapper extends BaseMapper<ProjectRequireProblem> {

    List<ProjectRequireProblemVo> page(IPage<ProjectRequireProblemVo> page, @Param(value = "projectRequireProblem") ProjectRequireProblemDto projectRequireProblemDto);

}
