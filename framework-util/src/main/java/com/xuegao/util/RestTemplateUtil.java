package com.xuegao.util;

import com.xuegao.config.web.RestTemplateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestTemplateUtil implements ApplicationListener<ApplicationStartedEvent>, ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateUtil.class);

    /**
     * 默认3秒
     */
    private static final long DEFAULT_SECONDS = 20;

    // private static RestTemplate REST_TEMPLATE = SpringUtil.getBean(RestTemplateConfig.REST_TEMPLATE_NAME, RestTemplate.class);
    private static RestTemplate REST_TEMPLATE;

    private ApplicationContext applicationContext;

    private RestTemplateUtil() {
    }

    @Override
    public void setApplicationContext(ApplicationContext inputApplicationContext) throws BeansException {
        applicationContext = inputApplicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        RestTemplate restTemplate = applicationContext.getBean(RestTemplateConfig.REST_TEMPLATE_NAME, RestTemplate.class);
        setRestTemplate(restTemplate);
        log.info("[xue-gao-framework][RestTemplateUtil][onApplicationEvent][设置restTemplate完毕]");
    }

    public static void setRestTemplate(RestTemplate restTemplate) {
        if (REST_TEMPLATE == null) {
            REST_TEMPLATE = restTemplate;
        }
    }

    private static RestTemplate getRestTemplate() {
        if (REST_TEMPLATE == null) {
            throw new RuntimeException("请自行设置REST_TEMPLATE或扫描使用RestTemplateUtil配置后，才可正常使用");
        }
        return REST_TEMPLATE;
    }

    public static <T> T sendGet(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        return getRestTemplate().getForObject(url, responseType, uriVariables);
    }

    public static <T> T sendGet(String url, Class<T> responseType, Object... uriVariables) {
        return getRestTemplate().getForObject(url, responseType, uriVariables);
    }

    /**
     * 默认都是Json格式
     * sendPost
     *
     * @param url:
     * @param request:
     * @param responseType:
     * @return T
     * @author xuegao
     * @date 2022/8/5 21:17
     */
    public static <T> T sendPost(String url, Object request, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return getRestTemplate().postForObject(url, request, responseType);
    }

    public static <T> T sendPost(String url, Object request, Class<T> responseType,
                                 Map<String, ?> uriVariables) {
        return getRestTemplate().postForObject(url, request, responseType, uriVariables);
    }

}
