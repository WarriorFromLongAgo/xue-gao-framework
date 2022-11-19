package com.xuegao.log.web;

import com.xuegao.core.common.FmkConstant;
import com.xuegao.core.model.Context;
import com.xuegao.core.model.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class FmkLogWebUtil extends FmkLogHandler {
    private static final Logger log = LoggerFactory.getLogger(FmkLogWebUtil.class);

    public static FmkLogWebUtil instance = new FmkLogWebUtil();

    private FmkLogWebUtil() {
    }

    public void preHandle(HttpServletRequest request) {
        String traceId = request.getHeader(FmkConstant.FMK_LOG_TRACE_ID_KEY);

        Context context = ContextUtil.get();
        if (context == null) {
            context = ContextUtil.setDefaultContext();
        }
        context.setTraceId(traceId);
        processRequest(context);
    }

    public void afterCompletion() {
        processResponseByClean();
    }
}
