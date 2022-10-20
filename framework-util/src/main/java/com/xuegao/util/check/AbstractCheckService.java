package com.xuegao.util.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

public interface AbstractCheckService {
    /**
     * checkIsTrue
     *
     * @param bool:
     * @param errorMsg:
     * @return com.xuegao.xuegaoframework.util.check.AbstractCheckService
     * @author xuegao
     * @date 2022/8/7 22:12
     */
    default AbstractCheckService checkIsTrue(Boolean bool, String errorMsg) {
        if (Boolean.TRUE.equals(bool)) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * checkIsFalse
     * 判断入参的字段是否为false
     *
     * @param bool:
     * @param errorMsg:
     * @return com.kyexpress.crm.offer.service.check.AbstractCheckService
     * @author fjm
     * @date 2022/4/25 15:13
     */
    default AbstractCheckService checkIsFalse(Boolean bool, String errorMsg) {
        if (Boolean.FALSE.equals(bool)) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * 判断入参的字段是否为空
     * checkIsNull
     *
     * @param object:
     * @param errorMsg:
     * @return com.xuegao.xuegaoframework.util.check.AbstractCheckService
     * @author xuegao
     * @date 2022/8/7 22:13
     */
    default AbstractCheckService checkIsNull(Object object, String errorMsg) {
        if (isRealEmpty(object)) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * checkIsNull
     * 判断入参的字段是否为空
     *
     * @param object:
     * @param errorMsg:
     * @return com.kyexpress.crm.offer.service.check.AbstractCheckService
     * @author fjm
     * @date 2022/4/25 15:13
     */
    default AbstractCheckService checkIsNotNull(Object object, String errorMsg) {
        if (isNotRealEmpty(object)) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * 判断入参的字段是否超过限制
     * checkLength
     *
     * @param size:
     * @param length:
     * @param errorMsg:
     * @return com.xuegao.xuegaoframework.util.check.AbstractCheckService
     * @author xuegao
     * @date 2022/8/7 22:13
     */
    default AbstractCheckService checkLength(int size, int length, String errorMsg) {
        if (size > length) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * 判断入参的字段是否为空
     * isRealEmpty
     *
     * @param object:
     * @return boolean
     * @author xuegao
     * @date 2022/8/7 22:13
     */
    static boolean isRealEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return StringUtils.isBlank(object.toString());
        }
        return ObjectUtils.isEmpty(object);
    }

    /**
     * 判断入参的字段是否不为空
     * isNotRealEmpty
     *
     * @param object:
     * @return boolean
     * @author xuegao
     * @date 2022/8/7 22:13
     */
    default boolean isNotRealEmpty(Object object) {
        return !isRealEmpty(object);
    }

    /**
     * 格式化字段最大值与最小值
     * format
     *
     * @param input:
     * @param min:
     * @param max:
     * @return T
     * @author fjm
     * @date 2022/9/13 16:46
     */
    static <T extends Comparable<T>> T format(T input, T min, T max) {
        if (input == null) {
            input = min;
        } else if (input.compareTo(min) <= 0) {
            input = min;
        } else if (input.compareTo(max) >= 0) {
            input = max;
        }
        return input;
    }

    /**
     * 检查是否是整数，包含正负数，小数，等
     * checkIsNumber
     *
     * @param str:
     * @return boolean
     * @author fjm
     * @date 2022/10/20 14:51
     */
    static boolean checkIsNumber(String str) {
        if (isRealEmpty(str)) {
            return false;
        }
        str = str.trim();

        try {
            BigDecimal bigDecimal = new BigDecimal(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}