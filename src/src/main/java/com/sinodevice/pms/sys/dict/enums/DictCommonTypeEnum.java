package com.sinodevice.pms.sys.dict.enums;

/**
 * Created by wdai on 2019/5/24.
 */
public enum DictCommonTypeEnum {

    LIST(0, "列表"), TREE(1, "树型");

    private final int code;
    private final String msg;

    DictCommonTypeEnum(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }

}
