package com.sinodevice.pms.project.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.core.web.BaseController;
import com.sinodevice.pms.project.user.entity.ProjectInfoUser;
import com.sinodevice.pms.project.user.service.IProjectInfoUserService;
import com.sinodevice.pms.sys.user.entity.User;
import com.sinodevice.pms.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 项目干系人 前端控制器
 * </p>
 *
 * @author wdai
 * @since 2020-05-31
 */
@RestController
@RequestMapping("/user/project/info/user")
public class ProjectInfoUserController extends BaseController<IProjectInfoUserService, ProjectInfoUser> {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public R<List<User>> page(Long projectId) {
        List<User> userList = userService.list(Wrappers.<User>lambdaQuery().in(User::getId,
                this.baseService.list(Wrappers.<ProjectInfoUser>lambdaQuery().eq(ProjectInfoUser::getProjectId, projectId)).stream()
                        .map(ProjectInfoUser::getUserId).collect(Collectors.toList()))
        );
        return R.ok(userList);
    }

}
