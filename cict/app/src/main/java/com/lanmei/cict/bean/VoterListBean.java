package com.lanmei.cict.bean;

/**
 * Created by xkai on 2018/12/5.
 */

public class VoterListBean {

    public String getName() {
        return name;
    }

    public boolean isClick() {
        return isClick;
    }

    public int getPicId() {
        return picId;
    }

    public String getNum() {
        return num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public void setNum(String num) {
        this.num = num;
    }

    private String name;
    private boolean isClick = true;
    private int picId;
    private String num;
}
