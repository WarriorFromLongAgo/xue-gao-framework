package com.xuegao.log.springtask;

import com.xuegao.core.model.Context;
import com.xuegao.core.model.ContextUtil;
import com.xuegao.log.web.FmkLogHandler;
import com.xuegao.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于spring scheduled定时器的增强AOP
 *
 * @author Bryan.Zhang
 * @since 1.3.4
 */
@Aspect
public class SpringScheduledTaskAop {
    private static final Logger log = LoggerFactory.getLogger(SpringScheduledTaskAop.class);

    private final FmkLogHandler fmkLogHandler = new FmkLogHandler();

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void cut() {
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        try {
            if (log.isDebugEnabled()) {
                Context context = ContextUtil.get();
                log.debug("[xue-gao-framework][SpringScheduledTaskAop][around][context1={}]", JsonUtil.toJsonString(context));
            }
            fmkLogHandler.processBefore(null);
            if (log.isDebugEnabled()) {
                Context context = ContextUtil.get();
                log.debug("[xue-gao-framework][SpringScheduledTaskAop][around][context2={}]", JsonUtil.toJsonString(context));
            }
            return jp.proceed();
        } finally {
            fmkLogHandler.processAfterByClean();
        }
    }
}
