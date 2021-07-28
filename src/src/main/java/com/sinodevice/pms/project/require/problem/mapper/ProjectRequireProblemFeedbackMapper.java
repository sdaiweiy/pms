package com.sinodevice.pms.project.require.problem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.project.require.problem.entity.ProjectRequireProblemFeedback;
import com.sinodevice.pms.project.require.problem.vo.ProjectRequireProblemFeedbackVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 需求文档反馈 Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2020-08-03
 */
@Mapper
public interface ProjectRequireProblemFeedbackMapper extends BaseMapper<ProjectRequireProblemFeedback> {

    List<ProjectRequireProblemFeedbackVo> list(@Param("problemId") Long problemId);

}
