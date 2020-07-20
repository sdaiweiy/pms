package com.sinodevice.pms.sys.org.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.org.entity.Org;
import com.sinodevice.pms.sys.org.service.IOrgService;
import com.sinodevice.pms.sys.user.service.IUserOrgService;
import com.sinodevice.pms.sys.user.vo.UserOrgSelectedVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统组织表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-11-07
 */
@Api(tags = {"组织"})
@RestController
@RequestMapping("/sys/org")
public class OrgController extends BaseController<IOrgService, Org> {

    @Autowired
    private IUserOrgService userOrgService;


    @ApiOperation(value = "查询所有组织")
    @GetMapping("/all/list")
    public R<List<Org>> list() {
        return success(baseService.listAll(true));
    }

    @ApiOperation(value = "根据 用户id 查询其所属组织")
    @GetMapping("/all/list_{userId}")
    public R<List<UserOrgSelectedVO>> listSelected(@PathVariable("userId") Long userId) {
        return success(userOrgService.listSelectedVO(userId));
    }

    @ApiOperation(value = "新增组织")
    @PostMapping("")
    public R<Boolean> add(Org org) {
        org.setSort(0);
        return success(baseService.save(org));
    }

}
