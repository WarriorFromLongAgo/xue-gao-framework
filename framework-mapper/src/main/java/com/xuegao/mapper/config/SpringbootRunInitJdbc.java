package com.xuegao.mapper.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 系统在初始化的时候，直接去获取链接
 * <p>
 * 如果调用数据库再去获取，第一次请求会很慢
 */
public class SpringbootRunInitJdbc implements ApplicationListener<ApplicationStartedEvent> {
    private static final Logger log = LoggerFactory.getLogger(SpringbootRunInitJdbc.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("[xue-gao-framework][SpringbootRunInitJdbc][onApplicationEvent][begin]");
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.info("[xue-gao-framework][SpringbootRunInitJdbc][onApplicationEvent][e={}]", e.getMessage(), e);
        }
        log.info("[xue-gao-framework][SpringbootRunInitJdbc][onApplicationEvent][end]");
    }
}
