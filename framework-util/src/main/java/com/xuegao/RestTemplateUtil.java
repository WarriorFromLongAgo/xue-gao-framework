package com.xuegao;

import com.xuegao.config.RestTemplateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateUtil {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateUtil.class);

    /**
     * 默认3秒
     */
    private static final long DEFAULT_SECONDS = 20;

    private static RestTemplate REST_TEMPLATE = SpringUtil.getBean(RestTemplateConfig.REST_TEMPLATE_NAME, RestTemplate.class);

    private RestTemplateUtil() {
    }

    public static void setRedisTemplate(RestTemplate restTemplate) {
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
     * @author fjm
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
