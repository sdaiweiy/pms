package com.sinodevice.pms.report.daily.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.report.daily.entity.ReportDaily;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author ZF
 * @create 2021/7/28 14:29
 */
@Data
public class ReportDailyPageDto extends ReportDaily {

    /**
     * 登录用户的id
     */
    private Long loginUserId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 创建人姓名
     */
    private String createByName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelBeginDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelEndData;

    //是否加班
    private Integer workExtra;

}
