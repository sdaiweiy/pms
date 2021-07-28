package com.sinodevice.pms.report.daily.service.impl;

import com.sinodevice.pms.report.daily.entity.ReportDailyDetails;
import com.sinodevice.pms.report.daily.mapper.ReportDailyDetailsMapper;
import com.sinodevice.pms.report.daily.service.IReportDailyDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.report.daily.vo.ReportDailyDetailsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 日报 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
@Service
public class ReportDailyDetailsServiceImpl extends ServiceImpl<ReportDailyDetailsMapper, ReportDailyDetails> implements IReportDailyDetailsService {

    @Override
    public List<ReportDailyDetailsVo> listByDailyId(Long dailyId) {
        return this.baseMapper.listByDailyId(dailyId);
    }
}
