package com.xuegao.core.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DelFlagEnum implements EnumInterFace {
    /**
     * 删除标志默认0未删除,1已删除,0未删除
     */
    FLAG_0(0, "未删除"),
    FLAG_1(1, "已删除"),

    ;

    private final int code;
    private final String msg;


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }


}
