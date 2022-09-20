package com.xuegao.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 去除str开头和末尾的 separator
     *
     * @param str:
     * @param separator:
     * @return java.lang.String
     * @author xuegao
     * @date 2021/9/12 12:52
     */
    public static String trimSeparator(String str, String separator) {
        str = str.replaceAll("^" + separator + "*", "");
        str = str.replaceAll(separator + "+$", "");
        return str;
    }

    /**
     * 去除str 末尾的 separator
     *
     * @param str:
     * @param separator:
     * @return java.lang.String
     * @author xuegao
     * @date 2021/9/12 12:52
     */
    public static String trimEndSeparator(String str, String separator) {
        str = str.replaceAll(separator + "+$", "");
        return str;
    }

    /**
     * 去除str开头和末尾的 separator
     * 去除开头和末尾的  0 和 .
     *
     * @param str:
     * @return java.lang.String
     * @author xuegao
     * @date 2021/9/12 12:52
     */
    public static String trimSeparatorByMoney(String str) {
        if (StringUtils.isBlank(str)) {
            return "0";
        }
        if (Objects.equals(str, "0")) {
            return "0";
        }
        if (!str.contains(".")) {
            return str;
        }
        String tempStr = trimSeparator(str, "0");
        return trimSeparator(tempStr, "\\.");
    }

    /**
     * 去除str开头和末尾的 separator
     * 去除开头和末尾的  0 和 .
     *
     * @param str:
     * @return java.lang.String
     * @author xuegao
     * @date 2021/9/12 12:52
     */
    public static String trimEndSeparatorByMoney(String str) {
        if (StringUtils.isBlank(str)) {
            return "0";
        }
        if (Objects.equals(str, "0")) {
            return "0";
        }
        if (!str.contains(".")) {
            return str;
        }
        String tempStr = trimEndSeparator(str, "0");
        tempStr = trimEndSeparator(tempStr, "\\.");
        if (StringUtils.isBlank(tempStr)) {
            return "0";
        }
        return tempStr;
    }

    public static List<String> splitTrim(String inputStr, String separator) {
        if (StringUtils.isBlank(inputStr) || StringUtils.isBlank(separator)) {
            return Lists.newArrayList();
        }
        // StringBuilder stringBuilder = new StringBuilder();
        List<String> resultList = new ArrayList<>();
        String[] split = inputStr.split(separator);
        for (String tempStr : split) {
            resultList.add(tempStr.trim());
        }
        // if (StringUtils.isNotRealEmpty(stringBuilder.toString())) {
        //     stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        // }
        return resultList;
    }

    public static void main(String[] args) {
        String s = trimEndSeparatorByMoney("50");
        System.out.println(s);
    }

}
