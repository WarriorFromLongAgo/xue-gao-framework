package com.xuegao.idcenter;


import org.junit.Test;

public class XuegaoDefaultIdGeneratorTest {

    @Test
    public void generateTraceId() {
        XuegaoIdGenerator xuegaoDefaultIdGenerator = new XuegaoDefaultIdGenerator();
        System.out.println(xuegaoDefaultIdGenerator.nextId());
        System.out.println(xuegaoDefaultIdGenerator.nextId());
        System.out.println(xuegaoDefaultIdGenerator.nextId());
    }


}
