package com.sinodevice.pms.report.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.report.week.entity.ReportWeekDetails;
import com.sinodevice.pms.report.week.mapper.ReportWeekDetailsMapper;
import com.sinodevice.pms.report.work.service.IReportWorkService;
import com.sinodevice.pms.report.work.vo.ReportWorkDailyVo;
import com.sinodevice.pms.report.work.vo.ReportWorkDetailsVo;
import com.sinodevice.pms.report.work.vo.ReportWorkWeekVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IReportWorkServiceImpl extends ServiceImpl<ReportWeekDetailsMapper, ReportWeekDetails> implements IReportWorkService {

    @Override
    public List<ReportWorkWeekVo> list(LocalDateTime beginTime, LocalDateTime endTime) {
        Map<String, List<ReportWorkDetailsVo>> reportWorkDetailsUserMap = this.baseMapper.listWorkDetails(beginTime, endTime)
                .stream().collect(Collectors.groupingBy(ReportWorkDetailsVo::getUserRealName, LinkedHashMap::new, Collectors.toList()));

        List<ReportWorkWeekVo> reportWorkWeekVoList = new ArrayList<ReportWorkWeekVo>();
        for (Map.Entry<String, List<ReportWorkDetailsVo>> userEntry : reportWorkDetailsUserMap.entrySet()) {
            ReportWorkWeekVo reportWorkWeekVo = new ReportWorkWeekVo();
            reportWorkWeekVo.setUserRealName(userEntry.getKey());

            //分割日期
            Map<String, List<ReportWorkDetailsVo>> reportWorkDetailsDayMap = userEntry.getValue().stream().collect(
                    Collectors.groupingBy(ReportWorkDetailsVo::getDay, LinkedHashMap::new, Collectors.toList()));

            List<ReportWorkDailyVo> reportWorkDailyVoList = new ArrayList<ReportWorkDailyVo>();
            for (Map.Entry<String, List<ReportWorkDetailsVo>> dayEntry : reportWorkDetailsDayMap.entrySet()) {
                ReportWorkDailyVo reportWorkDailyVo = new ReportWorkDailyVo();
                reportWorkDailyVo.setReportWorkDetailsVoList(dayEntry.getValue());
                reportWorkDailyVo.setDailyWorkHour(dayEntry.getValue().stream().map(ReportWorkDetailsVo::getWorkHour).reduce(BigDecimal::add).get());
                reportWorkDailyVoList.add(reportWorkDailyVo);
            }
            reportWorkWeekVo.setReportWorkDailyVoList(reportWorkDailyVoList);
            reportWorkWeekVo.setWeekWorkHour(reportWorkDailyVoList.stream().map(ReportWorkDailyVo::getDailyWorkHour).reduce(BigDecimal::add).get());
            reportWorkWeekVoList.add(reportWorkWeekVo);
        }

        return reportWorkWeekVoList;
    }

}
