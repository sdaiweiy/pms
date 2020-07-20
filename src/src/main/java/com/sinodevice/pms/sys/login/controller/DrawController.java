package com.sinodevice.pms.sys.login.controller;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.captcha.ImageCaptcha;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 验证码控制器
 * </p>
 *
 * @author hubin
 * @since 2018-08-09
 */
@Api(tags = {"绘图"})
@RestController
@RequestMapping("/draw")
public class DrawController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    public static final String TICKET = "pms_captcha_ticket";

    /**
     * 图片验证码
     */
    @Login(action = Action.Skip)
    @GetMapping("/captcha/image")
    public void image() {
        try {
            ImageCaptcha.getInstance().setLength(4).setInterfere(2)
                    .generate(request, response.getOutputStream(), TICKET);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 验证图片验证码是否正确
     */
    @PostMapping("/captcha/{code}")
    public Boolean validDefaultTime(@PathVariable String code) {
        return ImageCaptcha.getInstance().verification(request, TICKET, code);
    }
}