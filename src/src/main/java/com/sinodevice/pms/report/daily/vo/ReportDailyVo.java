package com.sinodevice.pms.report.daily.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.report.daily.entity.ReportDaily;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ZF
 * @create 2021/7/28 16:24
 */
@Data
public class ReportDailyVo extends ReportDaily {

    /**
     * 操作人
     */
    private String createByName;

    /**
     * 修改人
     */
    private String updateByName;

    /**
     * 项目详情
     */
    List<ReportDailyDetailsVo> details;

}
