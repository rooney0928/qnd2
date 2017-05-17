package com.app.qunadai.http.Exception;

import android.net.ParseException;

import com.app.qunadai.http.ApiException;
import com.app.qunadai.utils.LogU;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by wayne on 2017/5/16.
 */

public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int PARAM_ERROR = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int TOKEN_FAIL = 471;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case PARAM_ERROR:
                case UNAUTHORIZED:

                    try {
                        String errorJson = httpException.response().errorBody().string();
                        JSONObject obj = new JSONObject(errorJson);
                        String detail = obj.optString("detail");
                        ex.setDisplayMessage("错误" + httpException.code() + ":" + detail);

                    } catch (IOException e1) {
//                        e1.printStackTrace();
                        ex.setDisplayMessage("错误" + httpException.code() + ":读写异常");

                    } catch (JSONException e1) {
//                        e1.printStackTrace();
                        ex.setDisplayMessage("错误" + httpException.code() + ":json解析错误");
                    }
                    break;
                case INTERNAL_SERVER_ERROR:

                    ex.setDisplayMessage("错误" + httpException.code() + ":系统开小差啦,请稍后~~");
                    break;
                case TOKEN_FAIL:
                    ex.setTokenFail(true);
                    ex.setDisplayMessage("错误:登录失效,请重新登录");
                    break;
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage("错误" + httpException.code() + ":网络错误");  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.getCode());
            ex.setDisplayMessage(resultException.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ERROR.PARSE_ERROR);
            ex.setDisplayMessage("解析错误");            //均视为解析错误
            return ex;
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            ex = new ApiException(e, ERROR.NETWORD_ERROR);
            ex.setDisplayMessage("连接超时");  //均视为网络错误
            return ex;
        } else {
            ex = new ApiException(e, ERROR.UNKNOWN);
            ex.setDisplayMessage("未知错误");          //未知错误
            return ex;
        }
    }
}
