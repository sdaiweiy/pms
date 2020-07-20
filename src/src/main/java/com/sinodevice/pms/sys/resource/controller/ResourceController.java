package com.sinodevice.pms.sys.resource.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.resource.entity.Resource;
import com.sinodevice.pms.sys.resource.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统资源表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-09-13
 */
@Api(tags = {"资源"})
@RestController
@RequestMapping("/sys/resource")
public class ResourceController extends BaseController<IResourceService, Resource> {

    @ApiOperation(value = "分页查询 所有资源")
    @GetMapping("/page")
    public R<IPage<Resource>> page(Resource resource) {
        return success(baseService.page(getPage(), resource));
    }

    @ApiOperation(value = "查询 所有资源")
    @GetMapping("/list")
    public R<List<Resource>> list(Resource resource) {
        return success(baseService.list(Wrappers.<Resource>lambdaQuery()
                .setEntity(resource).orderByDesc(Resource::getSort).orderByAsc(Resource::getId)));
    }

    @ApiOperation(value = "查询开启状态的所有资源")
    @GetMapping("/list_effective")
    public R<List<Resource>> listEffective() {
        return success(baseService.listEffective());
    }

    @ApiOperation(value = "查询菜单")
    @GetMapping("/menu")
    public R<List<Resource>> listMenu() {
        return success(baseService.listMenuByUserId(getLoginUserId()));
    }

    @ApiOperation(value = "根据 id 更新状态")
    @PutMapping("/status_{id}")
    public R<Boolean> status(@PathVariable("id") Long id,
                             @RequestParam Integer status) {
        return success(baseService.changeStatus(id, status));
    }

    @ApiOperation(value = "新增权限")
    @PostMapping("")
    public R<Boolean> add(Resource resource) {
        resource.setStatus(0);
        return success(baseService.save(resource));
    }
}
