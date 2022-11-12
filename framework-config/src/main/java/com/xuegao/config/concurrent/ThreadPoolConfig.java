package com.xuegao.config.concurrent;

import com.xuegao.core.common.FmkConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@ConditionalOnMissingBean(name = {FmkConstant.XUEGAO_THREAD_NAME_BEAN})
@ConditionalOnProperty(
        prefix = "xuegao",
        name = "concurrent.enable",
        havingValue = "true"
)
@ConfigurationProperties(prefix = "xuegao.concurrent")
public class ThreadPoolConfig {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);

    @Value("${corePoolSize:8}")
    private Integer corePoolSize;
    @Value("${maximumPoolSize:16}")
    private Integer maximumPoolSize;
    @Value("${keepAliveSecond:60}")
    private Integer keepAliveSecond;
    @Value("${queueCapacity:1024}")

    private Integer queueCapacity;


    public ThreadPoolConfig() {
    }

    @Bean({FmkConstant.XUEGAO_THREAD_NAME_BEAN})
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(this.corePoolSize);
        taskExecutor.setMaxPoolSize(this.maximumPoolSize);
        taskExecutor.setQueueCapacity(this.queueCapacity);
        taskExecutor.setKeepAliveSeconds(this.keepAliveSecond);
        taskExecutor.setThreadNamePrefix(FmkConstant.XUEGAO_THREAD_NAME_PREFIX);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.initialize();
        log.info("[xue-gao-framework][ThreadPoolConfig][asyncTaskExecutor][线程池初始化完成]");
        return taskExecutor;
    }

}
