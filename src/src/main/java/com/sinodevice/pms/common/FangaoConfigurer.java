package com.sinodevice.pms.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * WEB 初始化相关配置
 * </p>
 *
 * @author jobob
 * @since 2018-09-23
 */
public class FangaoConfigurer implements WebMvcConfigurer {

//    @Autowired
//    protected HttpServletRequest request;

    /**
     * <p>
     * 字符串转换处理
     * </p>
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 日期转换
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new DateFormat() {
            private final List<? extends DateFormat> DATE_FORMATS = Arrays.asList(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                    new SimpleDateFormat("yyyy-MM-dd"),
                    new SimpleDateFormat("yyyy-MM"));

            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
                throw new UnsupportedOperationException("This custom date formatter can only be used to *parse* Dates.");
            }

            @Override
            public Date parse(String source, ParsePosition pos) {
                for (final DateFormat dateFormat : DATE_FORMATS) {
                    Date date = dateFormat.parse(source, pos);
                    if (null != date) {
                        return date;
                    }
                }
                return null;
            }
        }, true));
    }

    /**
     * <p>
     * 消息转换
     * </p>
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        final ObjectMapper objectMapper = builder.build();
        SimpleModule simpleModule = new SimpleModule();
        // Long 转为 String 防止 js 丢失精度
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        // 忽略 transient 关键词属性
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

}
