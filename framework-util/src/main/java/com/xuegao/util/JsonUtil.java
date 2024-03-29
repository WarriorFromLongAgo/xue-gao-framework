package com.xuegao.util;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * JSON 转换
 */
public final class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 把Java对象转换成json字符串
     *
     * @param object         待转化为JSON字符串的Java对象
     * @param throwException 是否要抛出异常
     * @return json 串 or null
     */
    public static String toJsonString(Object object, boolean throwException) {
        String string = null;
        try {
            string = JSON.toJSONString(object);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("[xuegao-im-chat-2022][JsonUtil][toJsonString][object={}]", JSON.toJSONString(object), e);
            }
            if (throwException) {
                e.printStackTrace();
                throw new RuntimeException("序列化异常", e);
            }
        }
        return string;
    }

    /**
     * 把Java对象转换成json字符串
     *
     * @param object 待转化为JSON字符串的Java对象
     * @return json 串 or null
     */
    public static String toJsonString(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        return toJsonString(object, Boolean.FALSE);
    }

    /**
     * 将Json字符串信息转换成对应的Java对象
     *
     * @param json json字符串对象
     * @param c    对应的类型
     */
    public static <T> T toClass(String json, Class<T> c) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return toClass(json, c, Boolean.FALSE);
    }

    /**
     * 将Json字符串信息转换成对应的Java对象
     *
     * @param json           json字符串对象
     * @param c              对应的类型
     * @param throwException 是否要抛出异常
     */
    public static <T> T toClass(String json, Class<T> c, boolean throwException) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return JSON.parseObject(json, c);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("[xuegao-im-chat-2022][JsonUtil][toClass][json={}]", json, e);
            }
            if (throwException) {
                e.printStackTrace();
                throw new RuntimeException("反序列化异常", e);
            }
        }
        return null;
    }
}
