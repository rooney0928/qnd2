package com.app.qunadai.http.Exception;

/**
 * Created by 12262 on 2016/5/31.
 * 与服务器约定好的异常
 */
public class ERROR {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;
    /**
     * token出错
     */
    public static final int TOKEN_ERROR = 1003;
}
