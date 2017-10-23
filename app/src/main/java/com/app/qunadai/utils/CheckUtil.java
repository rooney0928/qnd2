package com.app.qunadai.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wayne on 2017/9/15.
 */

public class CheckUtil {
    /**
     * 账户相关属性验证工具
     *
     */
    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1[34578]\\d{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";


    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：下载的文件格式
     */
    public static final String REGEX_FILE_TYPE = "avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|pdf|rar|zip|docx|doc|apk";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 校验有否字母
     *
     * @param content
     * @return
     */
    public static boolean hasAlpha(String content) {

        boolean flag = false;

        Pattern p = Pattern.compile(".*[a-zA-Z]+.*");

        Matcher m = p.matcher(content);

        if (m.matches())

            flag = true;

        return flag;
    }

    /**
     * 校验有否数字
     *
     * @param content
     * @return
     */
    public static boolean hasDigital(String content) {

        boolean flag = false;

        Pattern p = Pattern.compile(".*[0-9]+.*");

        Matcher m = p.matcher(content);

        if (m.matches())

            flag = true;

        return flag;
    }

    public static String findFileName(String path){
        Pattern pat=Pattern.compile("[\\w]+[\\.]("+REGEX_FILE_TYPE+")");//正则判断
        Matcher mc=pat.matcher(path);//条件匹配
        if(mc.find()){
            String substring = mc.group();//截取文件名后缀名
            return substring;
        }else{
            return "";
        }
    }
}
