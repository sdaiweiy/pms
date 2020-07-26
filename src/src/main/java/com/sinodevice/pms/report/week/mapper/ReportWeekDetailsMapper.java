package com.sinodevice.pms.report.week.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.week.vo.ReportWeekDetailsVo;
import com.sinodevice.pms.report.work.vo.ReportWorkDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
@Mapper
public interface ReportWeekDetailsMapper extends BaseMapper<ReportWeekDetails> {

    List<ReportWeekDetailsVo> list(@Param(value = "weekIndex") Long weekIndex);

    List<ReportWorkDetailsVo> listWorkDetails(@Param(value = "beginTime") LocalDateTime beginTime, @Param(value = "endTime") LocalDateTime endTime);

}
