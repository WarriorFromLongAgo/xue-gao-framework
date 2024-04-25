package com.xuegao.idcenter;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XuegaoDefaultIdGeneratorTest {

    @Test
    public void generateTraceId() {
        XuegaoIdGenerator xuegaoDefaultIdGenerator = new XuegaoDefaultIdGenerator();

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String nextId = xuegaoDefaultIdGenerator.nextId();
            stringList.add(nextId);
        }

        for (String s : stringList) {
            System.out.println(s);
        }
    }


}
