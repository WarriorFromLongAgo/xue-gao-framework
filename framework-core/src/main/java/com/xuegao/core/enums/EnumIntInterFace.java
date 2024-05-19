package com.xuegao.core.enums;

import java.util.Arrays;

public interface EnumIntInterFace {

    int getCode();

    String getMsg();

    // 根据 code 值获取对应的枚举
    static <T extends EnumIntInterFace> T getEnumByCode(Class<T> enumType, int inputCode) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.getCode() == inputCode)
                .findFirst()
                .orElse(null);
    }
}
