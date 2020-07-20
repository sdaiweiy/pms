package com.sinodevice.pms.common.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Spring Boot 相关辅助类
 * </p>
 *
 * @author jobob
 * @since 2018-09-23
 */
public class SpringHelper {

    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * <p>
     * 获取当前请求
     * </p>
     */
    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        return servletRequest;
    }

    /**
     * <p>
     * 设置 applicationContext
     * </p>
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (null == APPLICATION_CONTEXT) {
            APPLICATION_CONTEXT = applicationContext;
        }
    }

    /**
     * <p>
     * 获取 applicationContext
     * </p>
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * <p>
     * 通过class获取Bean
     * </p>
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return getApplicationContext().getBean(clazz);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
