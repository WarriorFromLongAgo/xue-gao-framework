package com.xuegao.mapper.enums;

import com.xuegao.mapper.model.GenericModel;

public enum DelFlagEnum {
    /**
     * {@link GenericModel#delFlag  0已删除，1默认值，未删除}
     */
    DEL_FLAG_1(1, "未删除"),
    DEL_FLAG_0(0, "已删除"),
    ;

    private final int code;
    private final String msg;

    DelFlagEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
