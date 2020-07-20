package com.sinodevice.pms.sys.role.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResourceZTreeVO implements Serializable {

    private Long id;
    @JsonProperty("pId")
    private Long pid;
    private String name;
    private Boolean checked;
    private Boolean open;

}
