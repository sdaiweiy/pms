package com.sinodevice.pms;

import com.sinodevice.pms.common.spring.SpringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.sinodevice.pms.config",
        "com.sinodevice.pms.*.**.controller",
        "com.sinodevice.pms.*.**.service",
        "com.sinodevice.pms.core.web"})
public class PMSApplication {
    private static final Logger logger = LoggerFactory.getLogger(PMSApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(PMSApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        SpringHelper.setApplicationContext(application.run(args));
        if (logger.isInfoEnabled()) {
            System.out.println("入口：http://localhost:8080");
            System.out.println("文档：http://localhost:8080/swagger-ui.html");
        }
    }

}
