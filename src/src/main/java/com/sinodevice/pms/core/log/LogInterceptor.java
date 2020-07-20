package com.sinodevice.pms.core.log;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.common.IpHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.sinodevice.pms.common.annotation.LogTrack;
import com.sinodevice.pms.common.utils.JacksonUtils;
import com.sinodevice.pms.sys.log.entity.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * <p>
 * 跟踪访问拦截处理器
 * </p>
 *
 * @author jobob
 * @since 2018-10-07
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    public LogInterceptor() {
        LogDisruptor.INSTANCE.start();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            // 日志跟踪注解解析
            LogTrack logTrack = method.getAnnotation(LogTrack.class);
            if (null != logTrack) {
                Log log = new Log();
                SSOToken st = SSOHelper.getSSOToken(request);
                if (null != st) {
                    log.setUserId(Long.valueOf(st.getId()));
                    log.setUsername(st.getIssuer());
                }
                log.setUri(request.getRequestURI());
                log.setIp(IpHelper.getIpAddr(request));
                log.setParams(method.getName() + ":" + JacksonUtils.toJSONString(request.getParameterMap()));
                log.setRemark(logTrack.value());
                log.setCreateTime(LocalDateTime.now());
                LogDisruptor.INSTANCE.publish(log);
            }
        }
    }

}

