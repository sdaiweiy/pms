package com.sinodevice.pms.config;

import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import com.sinodevice.pms.common.FangaoConfigurer;
import com.sinodevice.pms.common.LoginHandlerInterceptor;
import com.sinodevice.pms.sys.resource.entity.Resource;
import com.sinodevice.pms.sys.resource.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;


/**
 * <p>
 * WEB 初始化相关配置
 * </p>
 *
 * @author jobob
 * @since 2018-03-31
 */
@ControllerAdvice
@Configuration
public class SystemConfigurer extends FangaoConfigurer {

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private IResourceService resourceService;

    /**
     * 将自定义拦截器作为Bean写入配置
     *
     * @return
     */
    @Bean
    public FunctionHandlerInterceptor functionHandlerInterceptor() {
        return new FunctionHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // SSO 授权拦截器
        SSOSpringInterceptor ssoInterceptor = new SSOSpringInterceptor();
        ssoInterceptor.setHandlerInterceptor(new LoginHandlerInterceptor());
        registry.addInterceptor(ssoInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/sso/**", "/resource/nav_menu");

        // 功能权限拦截器
        // 查询所有功能权限
        List<Resource> functionList = resourceService.listFunctions();
        if (functionList != null && functionList.size() > 0) {
            // 当存在功能权限时，注入功能权限拦截器
            InterceptorRegistration interceptorRegistration = registry
                    .addInterceptor(functionHandlerInterceptor());
            // 将功能的请求地址加入拦截器
            for (Resource fuc : functionList)
                interceptorRegistration.addPathPatterns(
                        // 替换“{xxx}”为“*”
                        fuc.getUri().replaceAll("\\{\\w+\\}", "*")
                );
        }

        // 注入跟踪访问日志
//        registry.addInterceptor(new LogInterceptor());
    }

    /**
     * <p>
     * 跨域同源策略配置
     * </p>
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        /*        config.addAllowedOrigin("http://localhost:8080");*/
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
