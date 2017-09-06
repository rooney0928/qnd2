package com.app.qunadai.bean.base;

/**
 * Created by wayne on 2017/9/5.
 */

public class BaseBean<T> {
    // 接口请求返回带状态码
    private String code;
    // 服务器返回的提示信息
    private String msg;
    private String detail;
    //data数据
    private T content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
