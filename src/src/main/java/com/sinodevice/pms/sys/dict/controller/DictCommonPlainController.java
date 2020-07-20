package com.sinodevice.pms.sys.dict.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.dict.entity.DictCommonPlain;
import com.sinodevice.pms.sys.dict.service.IDictCommonPlainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 列表型字典  前端控制器
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
@RestController
@RequestMapping("/dict/common-plain")
public class DictCommonPlainController extends BaseController<IDictCommonPlainService, DictCommonPlain> {

    @ApiOperation(value = "分页查询 所有参数")
    @GetMapping("/page")
    public R<IPage<DictCommonPlain>> page(DictCommonPlain dictCommonPlain) {
        return success(baseService.page(getPage(), dictCommonPlain));
    }

    @ApiOperation(value = "根据 id 更新状态")
    @PutMapping("/status_{id}")
    public R<Boolean> status(@PathVariable("id") Long id,
                             @RequestParam Integer status) {
        return success(baseService.updateStatus(id, status));
    }

    @ApiOperation(value = "查询 所有资源")
    @GetMapping("/listByCode")
    public R<List<DictCommonPlain>> listByParentCode(String code) {
        List<DictCommonPlain> dictCommonPlainList = baseService.list(Wrappers.<DictCommonPlain>query().eq("status", "0").eq("code", code).orderByAsc("sort"));
        return success(dictCommonPlainList);
    }

}
