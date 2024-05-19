package com.xuegao.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDTO<T> {

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    private T queryData;
}