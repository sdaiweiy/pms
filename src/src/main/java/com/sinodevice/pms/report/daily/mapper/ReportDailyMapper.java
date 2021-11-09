package com.sinodevice.pms.report.daily.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinodevice.pms.report.daily.dto.ReportDailyPageDto;
import com.sinodevice.pms.report.daily.entity.ReportDaily;
import com.sinodevice.pms.report.daily.vo.ReportDailyPageVo;
import com.sinodevice.pms.report.daily.vo.ReportDailyVo;
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
public interface ReportDailyMapper extends BaseMapper<ReportDaily> {

    /**
     * 分页
     *
     * @param page
     * @param dto
     * @return
     */
    IPage<ReportDailyPageVo> page(IPage<ReportDailyPageVo> page, @Param("dto") ReportDailyPageDto dto);


    /**
     * 统计
     *
     * @param dto
     * @return
     */
    List<ReportDailyPageVo> list(@Param("dto") ReportDailyPageDto dto);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    ReportDailyVo getOneById(@Param("id") Long id);
}
