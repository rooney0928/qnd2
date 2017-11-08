package com.app.qunadai.bean.cpl;

/**
 * Created by wayne on 2017/11/8.
 */

public class CToken {

    /**
     * token : 2787E52C093AFA3B5D532DA844BF2EE7
     * name : 张三
     * id_card : 111111111111111111
     * mobile_number : 12345678910
     */

    private String token;
    private String name;
    private String id_card;
    private String mobile_number;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
