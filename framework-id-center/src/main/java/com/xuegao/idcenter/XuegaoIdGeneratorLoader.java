package com.xuegao.idcenter;

public class XuegaoIdGeneratorLoader {

    private static XuegaoIdGenerator idGenerator = new XuegaoDefaultIdGenerator();

    public static XuegaoIdGenerator getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(XuegaoIdGenerator idGenerator) {
        XuegaoIdGeneratorLoader.idGenerator = idGenerator;
    }
}
