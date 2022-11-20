package com.xuegao.log.web.filter;

import com.xuegao.core.common.FmkConstant;
import com.xuegao.core.model.ContextUtil;
import com.xuegao.log.web.FmkLogWebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个一个类做不到，因为try和finally会一起执行，但是traceId会直接写入到header里面
 * <p>
 * 由于filter的优先级，永远领先mvc的interceptor，所以先用这个类来实现traceId的赋值
 * <p>
 * 然后在interceptor里面进行处理一次调用的traceId
 */
public class FmkLogFilter implements Filter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(FmkLogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("[xue-gao-framework][FmkLogFilter][doFilter][try]");
                }
                FmkLogWebUtil.instance.preHandle((HttpServletRequest) request);
                //把traceId放入response的header，为了方便有些人有这样的需求，从前端拿整条链路的traceId
                ((HttpServletResponse) response).addHeader(FmkConstant.FMK_LOG_TRACE_ID_KEY, ContextUtil.get().getTraceId());
            } finally {
                if (log.isDebugEnabled()) {
                    log.debug("[xue-gao-framework][FmkLogFilter][doFilter][finally]");
                }
                FmkLogWebUtil.instance.afterCompletion();
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}