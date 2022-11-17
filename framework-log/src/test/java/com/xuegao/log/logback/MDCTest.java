package com.xuegao.log.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MDCTest {

    Logger log = (Logger) LoggerFactory.getLogger("com.xuegao.log");

    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    @Before
    public void setUp() {
        listAppender.start();
        log.addAppender(listAppender);
    }

    @Test
    public void testMdc() throws InterruptedException, ExecutionException {
        // log.info("0");
        final String uuidKey = "uuid";
        final String testUuid = UUID.randomUUID().toString();
        MDC.put(uuidKey, testUuid);
        System.out.println(testUuid);
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<?> submit = executorService.submit(() -> log.info("1"));

        submit.get();
        String msg = listAppender.list.get(0).getMDCPropertyMap().get(uuidKey);
        System.out.println(msg);
        MDC.remove(uuidKey);

        Future<?> submit2 = executorService.submit(() -> log.info("2"));
        submit2.get();
        String msg2 = listAppender.list.get(1).getMDCPropertyMap().get(uuidKey);

        Assert.assertEquals(testUuid, msg);
        Assert.assertNull(msg2);
    }
}
