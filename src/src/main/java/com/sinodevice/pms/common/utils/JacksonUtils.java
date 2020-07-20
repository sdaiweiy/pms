package com.sinodevice.pms.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * <p>
 * JSON 字符与对像转换
 * </p>
 *
 * @author jobob
 * @since 2018-10-07
 */
public class JacksonUtils {

    private static ObjectMapper OBJECT_MAPPER;

    static {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER = builder.build();
        // 忽略 未知 异常
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略 transient 关键词属性
        OBJECT_MAPPER.enable(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
    }

    /**
     * <p>
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * </p>
     *
     * @param jsonStr
     * @param valueType
     * @return IOException
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) throws IOException {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        return OBJECT_MAPPER.readValue(jsonStr, valueType);
    }

    /**
     * <p>
     * json数组转List<br/>
     * JacksonUtils.readValue(jsonStr, new TypeReference<List<对象>>(){})
     * </p>
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return IOException
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) throws IOException {
        return OBJECT_MAPPER.readValue(jsonStr, valueTypeRef);
    }

    /**
     * <p>
     * 把JavaBean转换为json字符串
     * </p>
     *
     * @param object
     * @return JsonProcessingException
     */
    public static String toJSONString(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

}
