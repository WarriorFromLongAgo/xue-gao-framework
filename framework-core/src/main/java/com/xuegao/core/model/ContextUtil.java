package com.xuegao.core.model;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Objects;

public class ContextUtil {
    private static final ThreadLocal<Context> CURRENT_LOCAL_CONTEXT = new TransmittableThreadLocal<>();

    public static Context get() {
        return CURRENT_LOCAL_CONTEXT.get();
    }

    public static void set(Context context) {
        CURRENT_LOCAL_CONTEXT.set(context);
    }

    public static void remove() {
        CURRENT_LOCAL_CONTEXT.remove();
    }

    public static void addGlobalParam(String key, Object value) {
        Context context = get();
        if (context == null) {
            set(new Context());
            context = get();
        }

        context.addGlobalParam(key, value);
    }

    public Object getGlobalParam(String key) {
        Object result = null;

        Context context = get();
        if (context != null) {
            result = context.getGlobalParam(key);
        }
        return result;
    }

    public static void setDefaultContext() {
        Context context = ContextUtil.get();
        if (Objects.isNull(context)) {
            context = new Context();
        }
        UserInfo userInfo = context.getUserInfo();
        if (Objects.isNull(userInfo)) {
            userInfo = new UserInfo();
            userInfo.setUserId(UserInfo.USERINFO_SYSTEM_NUMBER);
            userInfo.setUsername(UserInfo.USERINFO_SYSTEM);
            userInfo.setUserNumber(UserInfo.USERINFO_SYSTEM_NUMBER);
            context.setUserInfo(userInfo);
            ContextUtil.set(context);
        }
    }
}
