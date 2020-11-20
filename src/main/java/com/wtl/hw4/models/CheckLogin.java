package com.wtl.hw4.models;

import com.wtl.hw4.Data.LoginInfo;

public class CheckLogin { // 检查账号密码
    public static boolean Check(LoginInfo info) {
        if ("admin".equals(info.getUsername()) && "123456".equals(info.getPassword()))
            return true;
        else
            return false;
    }
}
