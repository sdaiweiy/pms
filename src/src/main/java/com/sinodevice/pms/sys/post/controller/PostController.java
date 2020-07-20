package com.sinodevice.pms.sys.post.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.sys.post.entity.Post;
import com.sinodevice.pms.sys.post.service.IPostService;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import com.sinodevice.pms.sys.user.vo.UserPostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统岗位表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-10-21
 */
@Api(tags = {"岗位"})
@RestController
@RequestMapping("/sys/post")
public class PostController extends BaseController<IPostService, Post> {

    @Autowired
    private IUserPostService userPostService;

    @GetMapping("/page")
    public R<IPage<Post>> page(Post post) {
        return success(baseService.page(getPage(), post));
    }

    @PutMapping("/status_{id}")
    public R<Boolean> status(@PathVariable("id") Long id, @RequestParam Integer status) {
        return success(baseService.updateStatus(id, status));
    }

    @ApiOperation(value = "根据 用户id 查询其所属角色")
    @GetMapping("/list_{userId}")
    public R<List<UserPostVo>> listSelected(@PathVariable("userId") Long userId) {
        return success(userPostService.listPostByUserId(userId));
    }


    @GetMapping("/list")
    public R<List<Post>> list() {
        return success(baseService.list(Wrappers.<Post>query().select("id", "name", "status").orderByAsc("sort")));
    }

}

