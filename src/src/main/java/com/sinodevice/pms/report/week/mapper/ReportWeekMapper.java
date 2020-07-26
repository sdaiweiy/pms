package com.sinodevice.pms.report.week.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinodevice.pms.report.week.entity.ReportWeek;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface ReportWeekMapper extends BaseMapper<ReportWeek> {

    List<ReportWeek> page(IPage<ReportWeek> page, @Param(value = "reportWeek") ReportWeek reportWeek);

    List<ReportWeek> pageTotal(IPage<ReportWeek> page, @Param(value = "reportWeek") ReportWeek reportWeek);

    List<ReportWeek> weekList(IPage<ReportWeek> page, @Param(value = "reportWeek") ReportWeek reportWeek, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("weekIndex") int weekIndex);
}
