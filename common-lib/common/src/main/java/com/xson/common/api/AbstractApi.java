package com.xson.common.api;

import android.content.Context;

import com.xson.common.bean.UserBean;
import com.xson.common.utils.L;
import com.xson.common.utils.StringUtils;
import com.xson.common.utils.UserHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Milk <249828165@qq.com>
 */
public abstract class AbstractApi {

    private int p;
    public static String API_URL = "";
    private String key = "yxg";
    public HashMap<String, Object> paramsHashMap = new HashMap<String, Object>();
    Method method = Method.POST;

    public static enum Method {
        GET,
        POST,
    }

    public static enum Enctype {
        TEXT_PLAIN,
        MULTIPART,
    }

    public void setMethod(Method method){
        this.method = method;
    }

    protected abstract String getPath();

    public Method requestMethod() {
        return Method.POST;
    }

    public Enctype requestEnctype() {
        return Enctype.TEXT_PLAIN;
    }

    public String getUrl() {
        return API_URL + getPath();
    }

    public void setPage(int page) {
        this.p = page;
    }

    public AbstractApi addParams(String key, Object value) {
        paramsHashMap.put(key, value);
        return this;
    }

    public String getUserId(Context context) {
        UserBean userBean = UserHelper.getInstance(context).getUserBean();
        return StringUtils.isEmpty(userBean) ? "" : userBean.getId()+"";
    }


    public Map<String, Object> getParams() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("key", key);
        for (Map.Entry<String, Object> item : paramsHashMap.entrySet()) {
            if (item.getValue() instanceof com.alibaba.fastjson.JSONArray) {
                params.put(item.getKey(), (com.alibaba.fastjson.JSONArray) item.getValue());
                L.d(L.TAG, item.getKey() + "," + item.getValue());
            } else {
                params.put(item.getKey(), item.getValue());
            }
        }
        if (p > 0) {
            params.put(L.p, p);
        }
        return params;
    }

}
