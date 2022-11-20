package com.xuegao.log.web;

import com.xuegao.core.common.FmkConstant;
import com.xuegao.core.model.Context;
import com.xuegao.core.model.ContextUtil;
import com.xuegao.idcenter.XuegaoIdGeneratorLoader;
import com.xuegao.util.LocalhostUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class FmkLogHandler {
    private static final Logger log = LoggerFactory.getLogger(FmkLogHandler.class);

    public void processRequest(Context context) {
        if (ObjectUtils.isEmpty(context)) {
            context = ContextUtil.setDefaultContext();
        }
        // 如果没有获取到，则重新生成一个traceId
        if (StringUtils.isBlank(context.getTraceId())) {
            context.setTraceId(XuegaoIdGeneratorLoader.getIdGenerator().nextId());
            if (log.isDebugEnabled()) {
                log.debug("[xue-gao-framework][FmkLogHandler][processProviderSide][重新生成traceId[{}]", context.getTraceId());
            }
        }
        String hostIp = LocalhostUtil.getHostIp();

        // 目前无论是不是MDC，都往MDC里放参数，这样就避免了log4j2的特殊设置
        MDC.put(FmkConstant.FMK_LOG_MDC_KEY, context.getTraceId() + FmkConstant.SYMBOL_UNDERLINE + hostIp);
        MDC.put(FmkConstant.FMK_LOG_TRACE_ID_KEY, context.getTraceId());
        MDC.put(FmkConstant.FMK_LOG_MDC_CURR_IP_KEY, hostIp);
        if (log.isDebugEnabled()) {
            log.debug("[xue-gao-framework][FmkLogHandler][processRequest][MDC.getTraceId={}]", MDC.get(FmkConstant.FMK_LOG_TRACE_ID_KEY));
        }
    }

    public void processResponseByClean() {
        Context context = ContextUtil.get();
        if (context == null) {
            context = ContextUtil.setDefaultContext();
        }
        context.setTraceId("");
        if (log.isDebugEnabled()) {
            log.debug("[xue-gao-framework][FmkLogHandler][processResponseByClean][MDC.getTraceId={}]", MDC.get(FmkConstant.FMK_LOG_TRACE_ID_KEY));
        }
        //移除MDC里的信息
        MDC.remove(FmkConstant.FMK_LOG_MDC_KEY);
        MDC.remove(FmkConstant.FMK_LOG_TRACE_ID_KEY);
        MDC.remove(FmkConstant.FMK_LOG_SPAN_ID_KEY);
        MDC.remove(FmkConstant.FMK_LOG_MDC_CURR_IP_KEY);
        if (log.isDebugEnabled()) {
            log.debug("[xue-gao-framework][FmkLogHandler][processResponseByClean][MDC.getTraceId={}]", MDC.get(FmkConstant.FMK_LOG_TRACE_ID_KEY));
        }
    }
}
