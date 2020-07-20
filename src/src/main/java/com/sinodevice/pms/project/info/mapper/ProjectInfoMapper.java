package com.sinodevice.pms.project.info.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinodevice.pms.project.info.entity.ProjectInfo;
import com.sinodevice.pms.project.info.vo.ProjectInfoVo;
import com.sinodevice.pms.project.info.vo.ProjectStatusVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
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
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {

    List<ProjectInfoVo> page(IPage<ProjectInfoVo> page, @Param(value = "projectInfo") ProjectInfo projectInfo);

    List<ProjectInfo> listByUserId(@Param(value = "userId") Long userId);

    List<ProjectStatusVo> listProjectStatus(@Param(value = "beginDate") LocalDate beginDate, @Param(value = "endDate") LocalDate endDate);

}
