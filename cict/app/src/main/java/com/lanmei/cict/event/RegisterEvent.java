package com.lanmei.cict.event;

/**
 * Created by xkai on 2018/12/5.
 * 注册事件
 */

public class RegisterEvent {

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public RegisterEvent(String mobile){
        this.mobile = mobile;
    }
}
