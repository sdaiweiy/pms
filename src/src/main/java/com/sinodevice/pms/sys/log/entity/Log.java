package com.sinodevice.pms.sys.log.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author jobob
 * @since 2018-10-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
public class Log extends SuperEntity {

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "请求URI")
    private String uri;

    @ApiModelProperty(value = "IP 地址")
    private String ip;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "删除 0、否 1、是")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
