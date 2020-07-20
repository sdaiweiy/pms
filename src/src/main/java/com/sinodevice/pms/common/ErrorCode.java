package com.sinodevice.pms.common;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;


/**
 * <p>
 * 错误码
 * </p>
 *
 * @author jobob
 * @since 2018-09-23
 */
public enum ErrorCode implements IErrorCode {

    NOT_LOGIN(-2, "未登录"),
    SUCCESS(0, "成功"),
    ID_REQUIRED(100, "主键 ID 必须存在"),
    ID_NOT_FOUND(101, "主键 ID 数据不存在"),
    EXIST_INHAND_EVENT(102, "存在待处理或处理中的事项"),
    NO_EXIST_EVENT(103, "事项不存在");

    private final long code;
    private final String msg;

    ErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static IErrorCode fromCode(long code) {
        ErrorCode[] ecs = ErrorCode.values();
        for (ErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return ApiErrorCode.SUCCESS;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}