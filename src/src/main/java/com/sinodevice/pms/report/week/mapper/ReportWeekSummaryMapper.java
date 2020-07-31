package com.sinodevice.pms.report.week.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.report.week.entity.ReportWeekSummary;
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
public interface ReportWeekSummaryMapper extends BaseMapper<ReportWeekSummary> {
}
