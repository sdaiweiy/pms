package com.sinodevice.pms.project.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinodevice.pms.project.task.dto.ProjectTaskDto;
import com.sinodevice.pms.project.task.entity.ProjectTask;
import com.sinodevice.pms.project.task.vo.ProjectTaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@Mapper
public interface ProjectTaskMapper extends BaseMapper<ProjectTask> {

    List<ProjectTaskVo> page(IPage<ProjectTaskVo> page, @Param(value = "projectTask") ProjectTaskDto projectTaskDto, @Param(value = "userId") Long userId);

}
