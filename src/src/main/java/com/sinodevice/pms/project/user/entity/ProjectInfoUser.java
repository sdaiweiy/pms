package com.sinodevice.pms.project.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目干系人
 * </p>
 *
 * @author wdai
 * @since 2020-05-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ProjectInfoUser对象", description = "项目干系人")
public class ProjectInfoUser extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "干系人编号")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "人员类型 0:干系人 1:开发人")
    @TableField("TYPE")
    private Long type;

}
