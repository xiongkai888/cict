package com.lanmei.cict.bean;

/**
 * Created by xkai on 2018/12/5.
 */

public class ManualBean {
    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getPicId() {
        return picId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    private int position;
    private String name;
    private int picId;
}
