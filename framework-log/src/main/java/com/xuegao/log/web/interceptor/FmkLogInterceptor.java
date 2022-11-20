package com.xuegao.log.web.interceptor;

import com.xuegao.core.common.FmkConstant;
import com.xuegao.core.model.ContextUtil;
import com.xuegao.log.web.FmkLogWebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * web controller的拦截器
 * <p>
 * 在filter处理完成后，这里才是真正的处理一次请求的traceid的地方
 * 请求开始，设置traceId，请求结束，清理traceId
 *
 * @author Bryan.Zhang
 * @since 1.1.5
 */
public class FmkLogInterceptor extends AbstractFmkLogInterceptor implements Ordered {
    private static final Logger log = LoggerFactory.getLogger(FmkLogInterceptor.class);

    @Override
    public boolean preHandleByHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        FmkLogWebUtil.instance.preHandle(request);
        if (log.isDebugEnabled()) {
            log.debug("[xue-gao-framework][FmkLogInterceptor][preHandleByHandlerMethod][TraceId={}]", MDC.get("fmk_TraceId"));
        }
        //把traceId放入response的header，为了方便有些人有这样的需求，从前端拿整条链路的traceId
        response.addHeader(FmkConstant.FMK_LOG_TRACE_ID_KEY, ContextUtil.get().getTraceId());
        return true;
    }

    @Override
    public void postHandleByHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletionByHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("[xue-gao-framework][FmkLogInterceptor][afterCompletionByHandlerMethod][TraceId={}]", MDC.get("fmk_TraceId"));
        }
        FmkLogWebUtil.instance.afterCompletion();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
