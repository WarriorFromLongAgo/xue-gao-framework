package com.xuegao.idcenter;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XuegaoDefaultIdGeneratorTest {

    @Test
    public void generateTraceId() {
        XuegaoIdGenerator xuegaoDefaultIdGenerator = new XuegaoDefaultIdGenerator();
        System.out.println(xuegaoDefaultIdGenerator.generateTraceId());
        System.out.println(xuegaoDefaultIdGenerator.generateTraceId());
        System.out.println(xuegaoDefaultIdGenerator.generateTraceId());
    }


}
