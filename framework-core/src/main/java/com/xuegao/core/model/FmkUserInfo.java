package com.xuegao.core.model;

import java.io.Serializable;

public class FmkUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String KEY_PREFIX = "XUEGAO-";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USER_NUMBER = "userNumber";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_NICKNAME = "nickname";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_IP = "ipAdder";

    public static final String USERINFO_SYSTEM = "system";
    /***
     * 用户ID
     * 数据默认1开始，那么0就是系统了
     */
    public static final String USERINFO_SYSTEM_NUMBER = "0";

    private String userId;
    private String userNumber;
    private String username;
    private String nickname;
    private String phone;
    private String ipAdder;

    public FmkUserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAdder() {
        return ipAdder;
    }

    public void setIpAdder(String ipAdder) {
        this.ipAdder = ipAdder;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
