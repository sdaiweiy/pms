package com.sinodevice.pms.core.web;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.common.ErrorCode;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.core.bean.SuperEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController<S extends IService<T>, T extends SuperEntity> extends ApiController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    @Autowired
    protected S baseService;

    public BaseController() {
    }

    @ApiOperation(
            value = "根据 id 查询",
            notes = "根据指定 id 查询一条数据"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "id",
            value = "主键",
            required = true,
            paramType = "path",
            dataType = "long"
    )})
    @GetMapping({"/{id}"})
    public R<T> get(@PathVariable Long id) {
        return this.success(this.baseService.getById(id));
    }

    @ApiOperation(
            value = "添加",
            notes = "添加一条数据"
    )
    @PostMapping
    public R<Boolean> add(T var1) {
        return this.success(this.baseService.save(var1));
    }

    @ApiOperation(
            value = "根据 id 修改",
            notes = "根据指定 id 修改该条数据内容"
    )
    @PutMapping
    public R<Boolean> edit(T var1) {
        Assert.fail(null == var1.getId(), ErrorCode.ID_REQUIRED);
        return this.success(this.baseService.updateById(var1));
    }

    @ApiOperation(
            value = "根据 id 删除",
            notes = "根据指定 id 删除该条数据内容"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "id",
            value = "主键",
            required = true,
            paramType = "path",
            dataType = "long"
    )})
    @DeleteMapping({"/{id}"})
    public R<Boolean> remove(@PathVariable("id") Long var1) {
        return this.success(this.baseService.removeById(var1));
    }

    public Page getPage() {
        long var1 = 1L;
        long var3 = 10L;
        String var5 = this.request.getParameter("page");
        if (StringUtils.isNotEmpty(var5)) {
            Long var6 = Long.valueOf(var5);
            if (var6 > 0L) {
                var1 = var6;
            }
        }

        String var8 = this.request.getParameter("limit");
        if (StringUtils.isNotEmpty(var5)) {
            Long var7 = Long.valueOf(var8);
            if (var7 > 0L) {
                var3 = var7;
            }
        }

        return new Page(var1, var3);
    }

    protected Long getLoginUserId() {
        return this.getLoginUser().getId();
    }

    protected Account getLoginUser() {
        return this.getLoginUser(true);
    }

    protected Account getLoginUser(boolean var1) {
        return LoginHelper.getAccount(this.request, var1);
    }
}