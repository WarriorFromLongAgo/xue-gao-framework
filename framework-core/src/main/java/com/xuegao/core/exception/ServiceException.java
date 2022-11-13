package com.xuegao.core.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//最高父类业务异常
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 430933593095358673L;

    private String errorMessage;

    private String errorCode;

    /**
     * 构造新实例。
     */
    public ServiceException() {
        super();
    }

    /**
     * 用给定的异常信息构造新实例。
     *
     * @param errorMessage 异常信息。
     */
    public ServiceException(String errorMessage) {
        super((String) null);
        this.errorMessage = errorMessage;
    }

    //省略部分代码

}