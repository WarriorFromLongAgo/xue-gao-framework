package com.xuegao.core.model;

import lombok.Getter;
import lombok.Setter;

//基础前后端交互实体，定义了前后端交互过程中，数据返回的标准格式
@Getter
@Setter
public class BaseResult {
    /**
     * httpCode
     */
    private Integer code;

    // /**
    //  * 业务code
    //  */
    // private Integer errorCode;

    /**
     * 业务信息
     */
    private String message;

    /**
     * 链路id【微服务请求调用链路跟踪，不了解此概念的，可以看一下我的另一篇博客：https://juejin.cn/post/6923004276335869960】
     */
    private String traceId;

    public BaseResult() {
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // public BaseResult(Integer code, Integer errorCode, String message) {
    //     this.code = code;
    //     // this.errorCode = errorCode;
    //     this.message = message;
    // }

    /**
     * 通用业务请求状态码
     */
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_SYSTEM_ERROR = 500;

    /**
     * 通用请求信息
     */
    public static final String SYSTEM_ERROR = "系统错误";
    public static final String MESSAGE_SUCCESS = "请求成功";
    public static final String QUERY_SUCCESS = "查询成功";
    public static final String INSERT_SUCCESS = "新增成功";
    public static final String UPDATE_SUCCESS = "更新成功";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String IMPORT_SUCCESS = "导入成功";
    public static final String EXPORT_SUCCESS = "导出成功";
    public static final String DOWNLOAD_SUCCESS = "下载成功";

}