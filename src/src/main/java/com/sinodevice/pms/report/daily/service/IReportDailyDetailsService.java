package com.sinodevice.pms.report.daily.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.report.daily.entity.ReportDailyDetails;
import com.sinodevice.pms.report.daily.vo.ReportDailyDetailsVo;

import java.util.List;

/**
 * <p>
 * 日报 服务类
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
public interface IReportDailyDetailsService extends IService<ReportDailyDetails> {

    /**
     * 根据日报id获取日报明细
     *
     * @param dailyId
     * @return
     */
    List<ReportDailyDetailsVo> listByDailyId(Long dailyId);

}
