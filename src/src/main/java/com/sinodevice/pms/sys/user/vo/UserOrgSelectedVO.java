package com.sinodevice.pms.sys.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserOrgSelectedVO implements Serializable{
    /**
     * 组织 ID
     */
    private Long id;
    /**
     * 组织 ID别名
     */
    @TableField(exist = false)
    private Long value;
    /**
     * 组织 pId
     */
    private Long pid;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 选择
     */
    private Boolean selected;
}
