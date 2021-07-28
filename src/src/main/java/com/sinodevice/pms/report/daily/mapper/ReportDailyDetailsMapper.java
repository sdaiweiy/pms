package com.sinodevice.pms.report.daily.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.report.daily.entity.ReportDailyDetails;
import com.sinodevice.pms.report.daily.vo.ReportDailyDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 日报 Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
@Mapper
public interface ReportDailyDetailsMapper extends BaseMapper<ReportDailyDetails> {

    /**
     * 根据日报id获取日报明细
     *
     * @param dailyId
     * @return
     */
    List<ReportDailyDetailsVo> listByDailyId(@Param("dailyId") Long dailyId);
}
