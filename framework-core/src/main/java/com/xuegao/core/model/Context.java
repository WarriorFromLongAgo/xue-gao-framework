package com.xuegao.core.model;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Context implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Context.class);

    private static final long serialVersionUID = 1L;

    /**
     * 全局参数
     */
    private /*transient*/ ConcurrentMap<String, Object> globalParamMap = new ConcurrentHashMap<>();
    /**
     * 一次登录，一个会话id
     */
    private String token;

    /**
     * 灰度标识
     */
    private String grayFlag;
    /**
     * IP地址
     * 当前服务器的IP地址
     */
    private String ipAddress;
    /**
     * 用户信息
     */
    private FmkUserInfo fmkUserInfo;
    /**
     * 请求者的用户id
     */
    private String userId;
    /**
     * 日志id
     */
    private String traceId;

    private ClientInfo clientInfo;

    public Context() {
    }

    public ClientInfo getClientInfo() {
        if (clientInfo == null) {
            clientInfo = new ClientInfo();
        }

        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getUserId() {
        return userId;
    }

    public String getGrayFlag() {
        return grayFlag;
    }

    public void setGrayFlag(String grayFlag) {
        this.grayFlag = grayFlag;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public FmkUserInfo getFmkUserInfo() {
        return fmkUserInfo;
    }

    public void setFmkUserInfo(FmkUserInfo fmkUserInfo) {
        this.fmkUserInfo = fmkUserInfo;
        if (ObjectUtils.isNotEmpty(fmkUserInfo) && ObjectUtils.isNotEmpty(fmkUserInfo.getUserId())) {
            this.setUserId(fmkUserInfo.getUserId());
        }
    }

    public ConcurrentMap<String, Object> getGlobalParamMap() {
        return globalParamMap;
    }

    public void setGlobalParamMap(ConcurrentMap<String, Object> globalParamMap) {
        this.globalParamMap = globalParamMap;
    }

    public void addGlobalParam(String key, Object value) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            log.warn("globalParamMap put null key or null value,but it,s Forbid,please check your code!");
            return;
        }
        globalParamMap.put(key, value);
    }

    public Object getGlobalParam(String key) {
        return globalParamMap.get(key);
    }
}
