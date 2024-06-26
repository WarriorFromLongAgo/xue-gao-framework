package com.xuegao.core.enums;

import java.util.Arrays;

public interface EnumStrInterFace {

    String getCode();

    String getMsg();

    // 根据 code 值获取对应的枚举
    static <T extends EnumStrInterFace> T getEnumByCode(Class<T> enumType, String inputCode) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.getCode().equals(inputCode))
                .findFirst()
                .orElse(null);
    }
}
