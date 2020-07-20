package com.sinodevice.pms.sys.role.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.resource.dto.RoleResourceDTO;
import com.sinodevice.pms.sys.role.entity.Role;
import com.sinodevice.pms.sys.role.service.IRoleResourceService;
import com.sinodevice.pms.sys.role.service.IRoleService;
import com.sinodevice.pms.sys.role.vo.ResourceZTreeVO;
import com.sinodevice.pms.sys.user.service.IUserRoleService;
import com.sinodevice.pms.sys.user.vo.UserRoleSelectedVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-09-15
 */
@Api(tags = {"角色"})
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController<IRoleService, Role> {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleResourceService roleResourceService;

    @ApiOperation(value = "分页查询 所有角色")
    @GetMapping("/page")
    public R<IPage<Role>> page(Role role) {
        return success(baseService.page(getPage(), role));
    }

    @ApiOperation(value = "查询 所有角色")
    @GetMapping("/list")
    public R<List<Role>> list() {
        return success(baseService.listAll());
    }

    @ApiOperation(value = "根据 用户id 查询其所属角色")
    @GetMapping("/list_{userId}")
    public R<List<UserRoleSelectedVO>> listSelected(@PathVariable("userId") Long userId) {
        return success(userRoleService.listSelectedVO(userId));
    }


    @GetMapping("/ztree_{id}")
    public R<List<ResourceZTreeVO>> listZTreeVO(@PathVariable("id") Long id) {
        return success(roleResourceService.listZTreeVO(id));
    }


    @PostMapping("/ztree")
    public R<Boolean> ztree(@RequestBody RoleResourceDTO dto) {
        return success(roleResourceService.saveByDto(dto));
    }

    @ApiOperation(value = "根据 id 更新状态")
    @PutMapping("/status_{id}")
    public R<Boolean> status(@PathVariable("id") Long id,
                             @RequestParam Integer status) {
        return success(baseService.updateStatus(id, status));
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("")
    public R<Boolean> add(Role role) {
        role.setStatus(0);
        return success(baseService.save(role));
    }
}
