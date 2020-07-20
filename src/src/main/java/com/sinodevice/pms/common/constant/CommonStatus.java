package com.sinodevice.pms.common.constant;


import com.sinodevice.pms.common.CommonEnum;

/**
 * <p>
 * 事项状态
 * </p>
 *
 * @author ykb
 * @since 2019-03-27
 */

public enum CommonStatus implements CommonEnum {

    INVALID(-1, "无效"), NORMAL(0, "有效");

    private final int code;
    private final String msg;

    CommonStatus(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
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