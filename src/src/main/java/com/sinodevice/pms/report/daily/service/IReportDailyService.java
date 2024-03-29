package com.sinodevice.pms.report.daily.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.report.daily.dto.ReportDailyDto;
import com.sinodevice.pms.report.daily.dto.ReportDailyPageDto;
import com.sinodevice.pms.report.daily.entity.ReportDaily;
import com.sinodevice.pms.report.daily.vo.ReportDailyPageVo;
import com.sinodevice.pms.report.daily.vo.ReportDailyVo;

import java.time.LocalDate;

/**
 * <p>
 * 日报 服务类
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
public interface IReportDailyService extends IService<ReportDaily> {

    /**
     * 分页查询
     *
     * @param page
     * @param dto
     * @return
     */
    IPage<ReportDailyPageVo> page(IPage<ReportDailyPageVo> page, ReportDailyPageDto dto);

    /**
     * 统计
     *
     * @param dto
     * @return
     */
    ReportDailyPageVo statistics(ReportDailyPageDto dto);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    ReportDailyVo getOneById(Long id);

    /**
     * 保存数据
     *
     * @param dto
     * @return
     */
    Boolean saveDto(ReportDailyDto dto);

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    Boolean updateDto(ReportDailyDto dto);

    /***
     * 核销加班
     * @param dailyIdList
     * @param cancelDay
     * @return
     */
    Boolean cancel(String[] dailyIdList, LocalDate cancelDay);
}
