package com.sinodevice.pms.sys.dict.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.dict.entity.DictCommon;
import com.sinodevice.pms.sys.dict.service.IDictCommonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 通用数据字典  前端控制器
 * </p>
 *
 * @author wdai
 * @since 2019-05-23
 */
@RestController
@RequestMapping("/dict/common")
public class DictCommonController extends BaseController<IDictCommonService, DictCommon> {

    @ApiOperation(value = "根据 查询所有顶级字典")
    @GetMapping("/listTop")
    public R<List<DictCommon>> selectTopList() {
        return success(baseService.selectTopList());
    }

    @ApiOperation(value = "查询 所有资源")
    @GetMapping("/list")
    public R<List<DictCommon>> list() {
        return success(baseService.list(Wrappers.<DictCommon>lambdaQuery().orderByAsc(DictCommon::getId)));
    }

    @ApiOperation(value = "查询 所有资源")
    @GetMapping("/listByParentCode")
    public R<List<DictCommon>> listByParentCode(String parentCode) {
        return success(baseService.listByParentCode(parentCode));
    }
}
