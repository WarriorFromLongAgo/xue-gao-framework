package com.xuegao.idcenter;


import com.xuegao.idcenter.snowflake.UniqueIdGenerator;

public class XuegaoDefaultIdGenerator extends XuegaoIdGenerator {
    @Override
    public String nextId() {
        return UniqueIdGenerator.nextStrId();
    }

}
