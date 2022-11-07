package com.xuegao.util.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

/**
 * 业务类
 *
 * @author xuegao
 * @version 1.0
 * @date 2022-3-10
 */
public interface AbstractCheckServiceV2 {
    /**
     * checkIsTrue
     * 判断入参的字段是否为true
     *
     * @param bool:
     * @param errorMsg:
     * @author xuegao
     * @date 2022/4/25 15:13
     */
    default AbstractCheckServiceV2 checkIsTrue(String errorMsg, Boolean bool) {
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
     * @author xuegao
     * @date 2022/4/25 15:13
     */
    default AbstractCheckServiceV2 checkIsFalse(String errorMsg, Boolean bool) {
        if (Boolean.FALSE.equals(bool)) {
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
     * @author xuegao
     * @date 2022/4/25 15:13
     */
    default AbstractCheckServiceV2 checkIsNull(String errorMsg, Object object) {
        if (isRealEmpty(object)) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * 检查入参是否都为空，只要有一个不为空，那么不报错
     * checkIsAllNull
     *
     * @param errorMsg:
     * @param object:
     * @author xuegao
     * @date 2022/11/7 12:02
     */
    default AbstractCheckServiceV2 checkIsAllNull(String errorMsg, Object... object) {
        boolean resultFlag = true;
        if (Objects.nonNull(object)) {
            for (Object o : object) {
                if (isNotRealEmpty(o)) {
                    resultFlag = false;
                }
            }
        }
        if (resultFlag) {
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
     * @author xuegao
     * @date 2022/4/25 15:13
     */
    default AbstractCheckServiceV2 checkIsNotNull(String errorMsg, Object object) {
        if (isNotRealEmpty(object)) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * checkLength
     * 判断是否相等，不相等直接报错
     *
     * @param o1:
     * @param o2:
     * @param errorMsg:
     * @author xuegao
     * @date 2022/4/25 15:14
     */
    default AbstractCheckServiceV2 checkEqual(String errorMsg, Object o1, Object o2) {
        if (Objects.equals(o1, o2)) {
            return this;
        }
        throw new RuntimeException(errorMsg);
    }

    /**
     * checkLength
     * 判断入参的字段是否超过限制
     *
     * @param size:
     * @param length:
     * @param errorMsg:
     * @author xuegao
     * @date 2022/4/25 15:14
     */
    default AbstractCheckServiceV2 checkLength(String errorMsg, int size, int length) {
        if (size > length) {
            throw new RuntimeException(errorMsg);
        }
        return this;
    }

    /**
     * isRealEmpty
     * 判断入参的字段是否为空
     *
     * @param object:
     * @return boolean
     * @author xuegao
     * @date 2022/4/25 15:15
     */
    default boolean isRealEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return StringUtils.isBlank(object.toString());
        }
        return ObjectUtils.isEmpty(object);
    }

    /**
     * isNotRealEmpty
     * 判断入参的字段是否不为空
     *
     * @param object:
     * @return boolean
     * @author xuegao
     * @date 2022/4/25 15:15
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
     * @author xuegao
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
     * 检查是否是数字
     * checkIsNumber
     *
     * @param errorMsg:
     * @param strArr:
     * @author xuegao
     * @date 2022/10/20 14:59
     */
    default AbstractCheckServiceV2 checkIsNumber(String errorMsg, String... strArr) {
        if (ObjectUtils.isEmpty(strArr)) {
            return this;
        }
        for (String str : strArr) {
            if (!checkIsNumber(str)) {
                throw new RuntimeException(errorMsg);
            }
        }
        return this;
    }

    /**
     * 检查是否是整数，包含正负数，小数，等
     * <p>
     * 为null，或者不是number，返回false
     * checkIsNumber
     *
     * @param t:
     * @return boolean
     * @author xuegao
     * @date 2022/10/20 14:51
     */
    default <T> boolean checkIsNumber(T t) {
        if (isRealEmpty(t)) {
            return false;
        }
        // String str = t.toString();
        try {
            BigDecimal bigDecimal = new BigDecimal(t.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * toNumber
     *
     * @param t:
     * @param function:
     * @return R
     * @author xuegao
     * @date 2022/11/2 16:50
     */
    default <T, R> R toNumber(T t, Function<String, R> function) {
        // if (isRealEmpty(t)) {
        //     return null;
        // }
        try {
            return function.apply(t.toString());
        } catch (Exception e) {
            return null;
        }
    }
}