package com.xuegao.core.model;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.lang3.StringUtils;

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

    public static Context setDefaultContext() {
        Context context = ContextUtil.get();
        if (Objects.isNull(context)) {
            context = new Context();
        }
        FmkUserInfo fmkUserInfo = context.getFmkUserInfo();
        if (Objects.isNull(fmkUserInfo)
                || StringUtils.isBlank(fmkUserInfo.getUserId())
                || StringUtils.isBlank(fmkUserInfo.getUserNumber())
                || StringUtils.isBlank(fmkUserInfo.getUsername())
        ) {
            fmkUserInfo = new FmkUserInfo();
            fmkUserInfo.setUserId(FmkUserInfo.USERINFO_SYSTEM_NUMBER);
            fmkUserInfo.setUsername(FmkUserInfo.USERINFO_SYSTEM);
            fmkUserInfo.setUserNumber(FmkUserInfo.USERINFO_SYSTEM_NUMBER);
            context.setFmkUserInfo(fmkUserInfo);
        }
        ContextUtil.set(context);
        return context;
    }
}
