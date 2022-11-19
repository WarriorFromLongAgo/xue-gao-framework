package com.xuegao.idcenter;


import com.xuegao.idcenter.diysnowflake.UniqueIdGenerator;

public class XuegaoDefaultIdGenerator extends XuegaoIdGenerator {
    @Override
    public String generateTraceId() {
        return UniqueIdGenerator.generateStringId();
    }

}
