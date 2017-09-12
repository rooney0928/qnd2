package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/11.
 */

public class Token {

    /**
     * access_token : 690f059a-88a4-4a21-817c-282cdf4eeb8f
     * uid : 7d933cdb-39e3-42e5-a028-bedf9d1579ac
     * userStatus : NORMAL
     * mobileNumber : 13162695551
     */

    private String access_token;
    private String uid;
    private String userStatus;
    private String mobileNumber;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
