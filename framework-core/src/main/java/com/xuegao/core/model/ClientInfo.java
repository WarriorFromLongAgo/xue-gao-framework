package com.xuegao.core.model;

import java.io.Serializable;

public class ClientInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ip
     */
    private String ipAdder;
    /**
     * 电脑名称
     */
    private String computerName;
    /**
     * 电脑编码
     */
    private String computerNumber;

    public String getIpAdder() {
        return ipAdder;
    }

    public void setIpAdder(String ipAdder) {
        this.ipAdder = ipAdder;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getComputerNumber() {
        return computerNumber;
    }

    public void setComputerNumber(String computerNumber) {
        this.computerNumber = computerNumber;
    }
}