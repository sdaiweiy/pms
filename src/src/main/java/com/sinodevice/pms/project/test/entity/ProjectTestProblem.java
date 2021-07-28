package com.sinodevice.pms.project.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinodevice.pms.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 功能测试反馈
 * </p>
 *
 * @author wdai
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("project_test_problem")
@ApiModel(value = "ProjectTestProblem对象", description = "功能测试反馈")
public class ProjectTestProblem extends BaseEntity {

    @ApiModelProperty(value = "问题关联的需求文档大纲")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "问题标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "问题描述")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "0:待处理 1:已反馈 2:待继续处理 3:已关闭")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "测试问题 1.界面UI 2.功能性问题  3.软件易用性  4.安装问题 5.安全性问题  6.兼容性问题 7.运行效率")
    @TableField("TYPE")
    private Integer type;

}
