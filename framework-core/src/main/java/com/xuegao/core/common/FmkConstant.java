package com.xuegao.core.common;

public class FmkConstant {

    // region 通用符号
    public static final String SYMBOL_POINT = ".";
    public static final String SYMBOL_UNDERLINE = "_";
    public static final String SYMBOL_COLON = ":";
    public static final String SYMBOL_COMMA = ",";
    // endregion

    /**
     * 最好使用feign
     * <p>
     * restTemplate 的 spring容器对象名称
     */
    public static final String REST_TEMPLATE_NAME = "xuegao-framework-restTemplate";
    /**
     * 线程池的前缀
     */
    public static final String XUEGAO_THREAD_NAME_PREFIX = "xuegao-SpringTaskExecutor-";
    /**
     * 线程池 的 spring容器对象名称
     */
    public static final String XUEGAO_THREAD_NAME_BEAN = "xuegaoSpringTaskExecutor";


    // region framework-log 的 一些常量
    /**
     * fmk_log mdc 的 key
     */
    public static final String FMK_LOG_MDC_KEY = "fmk_log";
    /**
     * fmk_log mdc 的 TraceId key
     */
    public static final String FMK_LOG_TRACE_ID_KEY = "fmk_TraceId";
    /**
     * fmk_log mdc 的 SpanId key
     */
    public static final String FMK_LOG_SPAN_ID_KEY = "fmk_SpanId";
    /**
     * fmk_log mdc 的 currIp key
     */
    public static final String FMK_LOG_MDC_CURR_IP_KEY = "currIp";
    // endregion
}
