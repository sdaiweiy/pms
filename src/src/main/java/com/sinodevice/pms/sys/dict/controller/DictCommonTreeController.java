package com.sinodevice.pms.sys.dict.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.dict.entity.DictCommonTree;
import com.sinodevice.pms.sys.dict.service.IDictCommonTreeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 通用-树键值对字典  前端控制器
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
@RestController
@RequestMapping("/dict/common-tree")
public class DictCommonTreeController extends BaseController<IDictCommonTreeService, DictCommonTree> {

    @ApiOperation(value = "查询 所有资源")
    @GetMapping("/list")
    public R<List<DictCommonTree>> list(DictCommonTree dictCommonTree) {
        return success(baseService.list(Wrappers.<DictCommonTree>lambdaQuery().setEntity(dictCommonTree).orderByAsc(DictCommonTree::getSort)));
    }

    @ApiOperation(value = "根据 id 更新状态")
    @PutMapping("/status_{id}")
    public R<Boolean> status(@PathVariable("id") Long id,
                             @RequestParam Integer status) {
        return success(baseService.updateStatus(id, status));
    }

    @ApiOperation(value = "查询 所有资源")
    @GetMapping("/listByCode")
    public R<List<DictCommonTree>> listByParentCode(DictCommonTree dictCommonTree) {
        return success(baseService.list(Wrappers.<DictCommonTree>query().eq("status", "0").setEntity(dictCommonTree).orderByAsc("sort")));
    }

}
