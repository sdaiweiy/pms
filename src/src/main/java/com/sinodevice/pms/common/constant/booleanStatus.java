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

public enum booleanStatus implements CommonEnum {

    NO(0, "否"), YES(1, "是");

    private final int code;
    private final String msg;

    booleanStatus(final int code, final String msg) {
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