package com.xson.common.helper;

import android.content.Context;

public class SharedAccount {

    private static SharedPreferencesTool sp;

    private SharedAccount() {
    }

    private static SharedAccount instance = null;

    public static SharedAccount getInstance(Context context) {
        if (instance == null) {
            instance = new SharedAccount();
        }
        sp = SharedPreferencesTool.getInstance(context, "account");
        return instance;
    }


    public void saveMobile(String mobile) {
        sp.edit().putString("mobile", mobile).commit();
    }
    public void saveName(String name) {
        sp.edit().putString("name", name).commit();
    }

    public void savePwd(String pwd) {
        sp.edit().putString("pwd", pwd).commit();
    }


    public void setNoFirstLogin(boolean isFirstLogin) {
        sp.edit().putBoolean("isFirstLogin", isFirstLogin).commit();
    }

    public String getMobile() {
        return sp.getString("mobile", "");
    }

    public String getName() {
        return sp.getString("name", "");
    }
    public String getPwd() {
        return sp.getString("pwd", "");
    }

    public boolean isFirstLogin() {
        return sp.getBoolean("isFirstLogin", false);
    }


    public void clear() {
        sp.clear();
    }


}
