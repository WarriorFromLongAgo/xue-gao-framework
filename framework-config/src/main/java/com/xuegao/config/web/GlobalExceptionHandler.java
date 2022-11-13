package com.xuegao.config.web;

import com.xuegao.core.exception.ServiceException;
import com.xuegao.core.model.BaseResult;
import com.xuegao.core.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public Object handle(Throwable ex) {
        log.info("[xue-gao-framework][GlobalExceptionHandler][handle][Throwable={}]", ex.getMessage(), ex);
        return Result.error(BaseResult.SYSTEM_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public Object handle(ServiceException ex) {
        log.info("[xue-gao-framework][GlobalExceptionHandler][handle][ServiceException={}]", ex.getMessage(), ex);
        // 注意，这里不是 message，而是自己的errorMsg
        // Result<Object> error = Result.error(ex.getErrorMessage());
        // log.info("[xue-gao-framework][GlobalExceptionHandler][handle][error={}]", error);
        // return error;
        return Result.error(ex.getMessage());
    }

}