package com.sinodevice.pms.config;

import com.baomidou.mybatisplus.extension.api.Assert;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.sys.resource.entity.Resource;
import com.sinodevice.pms.sys.resource.service.IResourceService;
import com.sinodevice.pms.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>
 * 权限拦截器
 * </p>
 *
 * @author jobob
 * @since 2018-11-13
 */
public class FunctionHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    public IResourceService resourceService;
    @Autowired
    public IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Account account = LoginHelper.getAccount(request,false);
        Assert.fail(account == null,"该账号存在异常，请重新登录");
        String uri = request.getRequestURI();
        List<Resource> fucList = resourceService.listFunctions();
        boolean flag = false;
        for(Resource fuc:fucList){
            String regex = fuc.getUri().replaceAll("\\{\\w+\\}","\\\\w+");
            if(Pattern.matches(regex, uri)
                    && userService.queryByUserIdAndResourceId(account.getId(),fuc.getId())){
                flag = true;
                break;
            }
        }
        Assert.fail(!flag,"您没有权限执行该操作");
        return super.preHandle(request, response, handler);
    }
}
