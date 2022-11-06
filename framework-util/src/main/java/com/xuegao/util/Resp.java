package com.xuegao.util;

import com.xuegao.core.enums.HttpCode;

import java.io.Serializable;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/6/4 15:03
 */
public class Resp<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -1L;

    private int code;

    private String message;

    private T data;

    public Resp() {
    }

    public Resp(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T extends Serializable> Resp<T> fail(T data) {
        return new Resp<>(HttpCode.SERVER_ERROR.getHttpCode(), HttpCode.SERVER_ERROR.getMessage(), data);
    }

    public static <T extends Serializable> Resp<T> success(T data) {
        return new Resp<>(HttpCode.SUCCESS_200.getHttpCode(), HttpCode.SUCCESS_200.getMessage(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}