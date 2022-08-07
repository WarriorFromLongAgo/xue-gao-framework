package com.xuegao.check;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
    default boolean isRealEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return StringUtils.hasText(object.toString());
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

}