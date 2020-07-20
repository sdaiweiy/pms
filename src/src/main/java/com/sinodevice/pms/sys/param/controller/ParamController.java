package com.sinodevice.pms.sys.param.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.param.entity.Param;
import com.sinodevice.pms.sys.param.service.IParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统参数表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-10-20
 */
@Api(tags = {"参数"})
@RestController
@RequestMapping("/sys/param")
public class ParamController extends BaseController<IParamService, Param> {

    @ApiOperation(value = "分页查询 所有参数")
    @GetMapping("/page")
    public R<IPage<Param>> page(Param param) {
        return success(baseService.page(getPage(), param));
    }

    @ApiOperation(value = "修改参数类型，1：系统参数，0：其他")
    @PutMapping("/sys_{id}")
    public R<Boolean> sys(@PathVariable("id") Long id, @RequestParam Integer sys) {
        return success(baseService.updateSys(id, sys));
    }
}
