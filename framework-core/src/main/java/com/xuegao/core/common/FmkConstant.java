package com.xuegao.core.common;

public class FmkConstant {
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
}
