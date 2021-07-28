package com.sinodevice.pms.project.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.project.test.entity.ProjectTestProblemFeedback;
import com.sinodevice.pms.project.test.vo.ProjectTestProblemFeedbackVo;
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
public interface ProjectTestProblemFeedbackMapper extends BaseMapper<ProjectTestProblemFeedback> {

    List<ProjectTestProblemFeedbackVo> list(@Param("problemId") Long problemId);

}
