package com.xuegao.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);

    /**
     * 最好使用feign
     */
    public static final String REST_TEMPLATE_NAME = "restTemplate";

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        // 连接超时设置 10s
        clientHttpRequestFactory.setConnectTimeout(10_000);
        // 读取超时设置 10s
        clientHttpRequestFactory.setReadTimeout(10_000);
        log.info("[xue-gao-framework][RestTemplateConfig][clientHttpRequestFactory][ClientHttpRequestFactory定义完成]");
        return clientHttpRequestFactory;
    }

    @Bean(REST_TEMPLATE_NAME)
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.clientHttpRequestFactory());
        log.info("[xue-gao-framework][RestTemplateConfig][restTemplate][restTemplate定义完成]");
        return restTemplate;
    }
}