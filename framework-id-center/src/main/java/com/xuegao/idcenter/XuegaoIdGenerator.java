package com.xuegao.idcenter;

import java.util.Map;

public abstract class XuegaoIdGenerator {

    protected Map<String, Object> extData;

    public abstract String nextId();

    public XuegaoIdGenerator withExtData(Map<String, Object> extData) {
        this.extData = extData;
        return this;
    }
}
