package com.sinodevice.pms.sys.login.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.common.annotation.LogTrack;
import com.sinodevice.pms.common.utils.JacksonUtils;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.sys.login.dto.LoginDTO;
import com.sinodevice.pms.sys.log.entity.Log;
import com.sinodevice.pms.sys.user.entity.User;
import com.sinodevice.pms.sys.log.service.ILogService;
import com.sinodevice.pms.sys.user.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 单点登录
 * </p>
 *
 * @author jobob
 * @since 2018-10-12
 */
@Api(tags = {"单点登录"})
@RestController
@RequestMapping("/sso")
public class SSOController extends ApiController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILogService logService;


    /**
     * 用户登录
     */
    @LogTrack("登录")
    @Login(action = Action.Skip)
    @PostMapping("/login")
    public R<User> login(LoginDTO dto) {
        return success(userService.loginByDto(request, response, dto));
    }


    /**
     * 用户退出
     */
    @LogTrack("退出")
    @Login(action = Action.Skip)
    @GetMapping("/logout")
    public void logout() {
        try {
            Account account = LoginHelper.getAccount(false);
            Log log = new Log();
            log.setUserId(account.getId());
            log.setUsername(account.getName());
            log.setUri(request.getRequestURI());
            log.setIp(request.getRemoteAddr());
            log.setParams("logout:"+ JacksonUtils.toJSONString(request.getParameterMap()));
            log.setRemark("退出");
            logService.add(log);
            SSOHelper.logout(request, response);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
