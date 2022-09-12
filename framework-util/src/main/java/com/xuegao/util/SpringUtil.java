package com.xuegao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 如果发现空指针，请看一下类的扫描路径
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    public static String APPLICATION_NAME = null;

    private static ApplicationContext APPLICATION_CONTEXT = null;

    public static Object getBean(String beanName) {
        if (APPLICATION_CONTEXT != null) {
            try {
                return APPLICATION_CONTEXT.getBean(beanName);
            } catch (Exception e) {
                logger.warn("获取Bean失败！ beanName: " + beanName, e);
                return null;
            }
        }

        return null;
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        T result = null;
        if (APPLICATION_CONTEXT != null) {
            try {
                result = APPLICATION_CONTEXT.getBean(beanName, requiredType);
            } catch (Exception e) {
                logger.warn("获取Bean失败！ beanName: " + beanName, e);
            }
        }

        return result;
    }

    public static <T> T getBean(Class<T> requiredType) {
        T result = null;
        if (APPLICATION_CONTEXT != null) {
            try {
                result = APPLICATION_CONTEXT.getBean(requiredType);
            } catch (Exception e) {
                logger.warn("获取Bean失败！", e);
            }
        }

        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_NAME = applicationContext.getId();
        SpringUtil.APPLICATION_CONTEXT = applicationContext;
    }
}
