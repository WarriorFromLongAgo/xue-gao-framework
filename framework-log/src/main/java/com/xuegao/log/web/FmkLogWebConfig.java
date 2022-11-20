package com.xuegao.log.web;

import com.xuegao.log.web.interceptor.FmkLogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class FmkLogWebConfig implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(FmkLogWebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("[xue-gao-framework][FmkLogWebConfig][addInterceptors][FmkLogInterceptor]");
        registry.addInterceptor(new FmkLogInterceptor());
    }
}
