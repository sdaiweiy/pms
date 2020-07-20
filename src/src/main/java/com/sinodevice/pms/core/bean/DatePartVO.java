package com.sinodevice.pms.core.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DatePartVO {
    private String start;
    private String end;
    private List<String> dateList;

    public DatePartVO() {
    }

    public DatePartVO(String start, String end) {
        this.start = start;
        this.end = end;
    }
}
