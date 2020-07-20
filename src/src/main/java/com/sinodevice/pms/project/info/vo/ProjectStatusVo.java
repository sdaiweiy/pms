package com.sinodevice.pms.project.info.vo;

import com.sinodevice.pms.project.info.entity.ProjectInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectStatusVo extends ProjectInfo {

    private int total;

    private int finish;

    private int unFinish;

    private BigDecimal workHour;

    private int thisWeekCount;

    private String realName;

}
