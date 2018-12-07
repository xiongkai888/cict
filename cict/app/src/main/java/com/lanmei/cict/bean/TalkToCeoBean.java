package com.lanmei.cict.bean;

/**
 * Created by xkai on 2018/12/5.
 */

public class TalkToCeoBean {

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getPicId() {
        return picId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    private String name;
    private String content;
    private int picId;
}
