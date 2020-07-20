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

public enum EventStatus implements CommonEnum {

    GIVE_UP(-1, "放弃处理"), WAIT_FOR(0, "待处理"), IN_HAND(1, "处理中"), HANDLED(2, "已处理");

    private final int code;
    private final String msg;

    EventStatus(final int code, final String msg) {
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