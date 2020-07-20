package com.sinodevice.pms.report.week.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.week.mapper.ReportWeekDetailsMapper;
import com.sinodevice.pms.report.week.service.IReportWeekDetailsService;
import com.sinodevice.pms.report.week.vo.ReportWeekDetailsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-02-09
 */
@Service
public class ReportWeekDetailsServiceImpl extends ServiceImpl<ReportWeekDetailsMapper, ReportWeekDetails> implements IReportWeekDetailsService {

    @Override
    public List<ReportWeekDetailsVo> list(Long weekIndex) {
        return this.baseMapper.list(weekIndex);
    }

}
