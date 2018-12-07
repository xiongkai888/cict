package com.xson.common.api;

/**
 * Created by xkai on 2018/11/26.
 */

public class OuQiApi extends AbstractApi{

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public OuQiApi(){
    }

    public OuQiApi(String path){
        this.path = path;
    }

    @Override
    protected String getPath() {
        return path;
    }
}
