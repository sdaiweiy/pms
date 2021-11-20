package com.sinodevice.pms.report.daily.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.report.daily.dto.ReportDailyDto;
import com.sinodevice.pms.report.daily.dto.ReportDailyPageDto;
import com.sinodevice.pms.report.daily.entity.ReportDaily;
import com.sinodevice.pms.report.daily.service.IReportDailyService;
import com.sinodevice.pms.report.daily.vo.ReportDailyPageVo;
import com.sinodevice.pms.report.daily.vo.ReportDailyVo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * <p>
 * 日报 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2021-07-28
 */
@RestController
@RequestMapping("/report/daily")
public class ReportDailyController extends BaseController<IReportDailyService, ReportDaily> {

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @GetMapping("/page")
    public R<IPage<ReportDailyPageVo>> page(ReportDailyPageDto dto) {
        return success(baseService.page(getPage(), dto));
    }

    /**
     * 统计
     *
     * @param dto
     * @return
     */
    @GetMapping("/statistics")
    public R<ReportDailyPageVo> statistics(ReportDailyPageDto dto) {
        return success(baseService.statistics(dto));
    }

    /**
     * 根据id获取数据
     *
     * @param dto
     * @return
     */
    @GetMapping("/getOneById")
    public R<ReportDailyVo> getOneById(ReportDaily dto) {
        return success(baseService.getOneById(dto.getId()));
    }

    /**
     * 保存数据
     *
     * @param dto
     * @return
     */
    @PostMapping("/save/dto")
    public R<Boolean> saveDto(@RequestBody ReportDailyDto dto) {
        return success(baseService.saveDto(dto));
    }

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    @PostMapping("/update/dto")
    public R<Boolean> updateDto(@RequestBody ReportDailyDto dto) {
        return success(baseService.updateDto(dto));
    }


    /**
     * @param
     * @return
     * @ 核销加班
     */
    @PostMapping("/cancel")
    public R<Boolean> cancel(String dailyIds, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate cancelDay) {
        String[] dailyIdList = dailyIds.split(";");
        return success(baseService.cancel(dailyIdList, cancelDay));
    }

}
