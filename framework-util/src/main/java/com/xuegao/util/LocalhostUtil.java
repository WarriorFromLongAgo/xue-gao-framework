package com.xuegao.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * 主要用来获取本地host和ip的工具类，带缓存
 *
 * @author Bryan.Zhang
 * @since 1.3.0
 */
public class LocalhostUtil {

    public static String LOCAL_HOST_IP;
    public static String LOCAL_HOST_NAME;

    public static String getHostIp() {
        try {
            LOCAL_HOST_IP = getLocalhostStr();
        } catch (Exception e) {
        }
        return LOCAL_HOST_IP;
    }

    public static String getHostName() {
        try {
            LOCAL_HOST_NAME = getLocalHostName();
        } catch (Exception e) {
        }
        return LOCAL_HOST_NAME;
    }

    private static String getLocalhostStr() {
        InetAddress localhost = getLocalhost();
        if (null != localhost) {
            return localhost.getHostAddress();
        }
        return null;
    }

    private static String getLocalHostName() {
        if (StringUtils.isNotBlank(LOCAL_HOST_NAME)) {
            return LOCAL_HOST_NAME;
        }

        final InetAddress localhost = getLocalhost();
        if (null != localhost) {
            String name = localhost.getHostName();
            if (StringUtils.isEmpty(name)) {
                name = localhost.getHostAddress();
            }
            LOCAL_HOST_NAME = name;
        }
        return LOCAL_HOST_NAME;
    }


    private static InetAddress getLocalhost() {
        final LinkedHashSet<InetAddress> localAddressList = localAddressList();

        if (ObjectUtils.isNotEmpty(localAddressList)) {
            InetAddress address2 = null;
            for (InetAddress inetAddress : localAddressList) {
                if (!inetAddress.isSiteLocalAddress()) {
                    // 非地区本地地址，指10.0.0.0 ~ 10.255.255.255、172.16.0.0 ~ 172.31.255.255、192.168.0.0 ~ 192.168.255.255
                    return inetAddress;
                } else if (null == address2) {
                    address2 = inetAddress;
                }
            }

            if (null != address2) {
                return address2;
            }
        }

        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // ignore
        }

        return null;
    }

    private static LinkedHashSet<InetAddress> localAddressList() {
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        if (networkInterfaces == null) {
            throw new RuntimeException("Get network interface error!");
        }

        final LinkedHashSet<InetAddress> ipSet = new LinkedHashSet<>();

        while (networkInterfaces.hasMoreElements()) {
            final NetworkInterface networkInterface = networkInterfaces.nextElement();
            final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                final InetAddress inetAddress = inetAddresses.nextElement();
                if (Objects.isNull(inetAddress) || inetAddress.isLoopbackAddress()) {
                    continue;
                }
                // 需为IPV4地址
                if (inetAddress instanceof Inet4Address) {
                    ipSet.add(inetAddress);
                }
            }
        }

        return ipSet;
    }
}
