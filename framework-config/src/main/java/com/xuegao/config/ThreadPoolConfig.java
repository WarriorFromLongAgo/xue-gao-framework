package com.xuegao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author cjy
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {
    /**
     * 获取服务器的cpu个数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int COUR_SIZE = CPU_COUNT * 2;

    private static final int MAX_COUR_SIZE = CPU_COUNT * 4;

    @Bean(name = "commonThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor commonThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPoolTaskExecutor.setCorePoolSize(COUR_SIZE);
        // 配置最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(MAX_COUR_SIZE);
        // 配置队列容量（这里设置成最大线程数的四倍）
        threadPoolTaskExecutor.setQueueCapacity(MAX_COUR_SIZE * 4);
        // 给线程池设置名称
        threadPoolTaskExecutor.setThreadNamePrefix("common-thread-");
        // 设置任务的拒绝策略
        // 线程不够用时由调用的线程处理该任务
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
        // 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        threadPoolTaskExecutor.setAwaitTerminationSeconds(300);

        log.info("[trobs-common-core][ThreadPoolConfig][commonThreadPoolTaskExecutor][线程池初始化完成]");
        return threadPoolTaskExecutor;
    }

}
