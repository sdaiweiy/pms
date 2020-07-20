package com.sinodevice.pms.common;

import com.baomidou.kisso.web.handler.SSOHandlerInterceptor;
import com.baomidou.mybatisplus.extension.api.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Spring 相关辅助类
 * </p>
 *
 * @author jobob
 * @since 2018-09-23
 */
public class LoginHandlerInterceptor implements SSOHandlerInterceptor {

    @Override
    public boolean preTokenIsNullAjax(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return false;
    }

    @Override
    public boolean preTokenIsNull(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Assert.fail(ErrorCode.NOT_LOGIN);
        return false;
    }
}
