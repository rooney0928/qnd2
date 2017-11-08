package com.app.qunadai.bean.base;

/**
 * Created by wayne on 2017/11/8.
 */

public class CplBase<T> {

    /**
     * status_code : 200
     * message : success
     */

    private int status_code;
    private String message;
    private T data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
