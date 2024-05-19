package com.xuegao.core.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConsatntYesNoEnumInt implements EnumIntInterFace {
    /**
     * 10 yes
     * 20 no
     */
    YES(10, "yes"),
    NO(20, "no"),

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
