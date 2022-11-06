package com.xuegao.core.model;

// @SpringBootTest
public class TestContextUtil {

    public static void main(String[] args) {
        ContextUtil.setDefaultContext();
        Context context = ContextUtil.get();
        FmkUserInfo fmkUserInfo = context.getUserInfo();


    }


}
