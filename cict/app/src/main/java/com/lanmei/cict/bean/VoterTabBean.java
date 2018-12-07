package com.lanmei.cict.bean;

/**
 * Created by xkai on 2018/12/7.
 */

public class VoterTabBean {

    public String getName() {
        return name;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    private String name;
    private boolean isChoose;


}
