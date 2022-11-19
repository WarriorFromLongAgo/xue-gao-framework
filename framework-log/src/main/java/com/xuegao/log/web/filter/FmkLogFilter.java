package com.xuegao.log.web.filter;

import com.xuegao.core.common.FmkConstant;
import com.xuegao.core.model.ContextUtil;
import com.xuegao.log.web.FmkLogWebUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FmkLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            try {
                FmkLogWebUtil.instance.preHandle((HttpServletRequest) request);
                //把traceId放入response的header，为了方便有些人有这样的需求，从前端拿整条链路的traceId
                ((HttpServletResponse) response).addHeader(FmkConstant.FMK_LOG_TRACE_ID_KEY, ContextUtil.get().getTraceId());
            } finally {
                FmkLogWebUtil.instance.afterCompletion();
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}