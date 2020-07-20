package com.sinodevice.pms.config;


import com.baomidou.mybatisplus.extension.api.R;
import com.sinodevice.pms.common.web.WebException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 通用 Api Controller 全局异常处理
 * </p>
 *
 * @author jobob
 * @since 2018-09-27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * <p>
     * 自定义 REST 业务异常
     * <p>
     *
     * @param e 异常类型
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public R<Object> handleBadRequest(Exception e) {
        return WebException.handleBadRequest(e);
    }
}