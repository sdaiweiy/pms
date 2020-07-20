package com.sinodevice.pms.project.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcess;
import com.sinodevice.pms.project.task.vo.ProjectTaskProcessVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public interface ProjectTaskProcessMapper extends BaseMapper<ProjectTaskProcess> {

    List<ProjectTaskProcessVo> listThisWeekTask(@Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    BigDecimal getTaskWorkHour(@Param("userId") Long userId, @Param("taskId") Long taskId);

    LocalDateTime getLastBeginTime(@Param("userId") Long userId, @Param("taskId") Long taskId);

}
