package com.xuegao.log.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import org.slf4j.TtlMDCAdapter;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by wuwen on 16/8/25.
 */
public class TtlMdcListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {
    private volatile boolean started = false;

    @Override
    public void start() {
        System.out.println("TtlMdcListener start");
        if (started) {
            return;
        }
        addInfo("[xue-gao-framework][load TtlMdcListener][start]");
        // Context context = getContext();
        // context.putProperty("pid", String.valueOf(getPid()));
        TtlMDCAdapter.getInstance();
        started = true;
    }

    @Override
    public void stop() {
        System.out.println("TtlMdcListener stop");
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext loggerContext) {
        System.out.println("TtlMdcListener onStart");
    }

    @Override
    public void onReset(LoggerContext loggerContext) {
        System.out.println("TtlMdcListener onReset");
    }

    @Override
    public void onStop(LoggerContext loggerContext) {
        System.out.println("TtlMdcListener onStop");
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
        System.out.println("TtlMdcListener onLevelChange");
    }

    private int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        // format: "pid@hostname"
        String name = runtime.getName();
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }
}
