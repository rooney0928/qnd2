package com.app.qunadai.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wayne on 2017/5/10.
 */

public class CommUtil {
    private static int REFRESH = 0;
    private static int LOAD_MORE = 1;

    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts
     *            数据源
     * @return 16进制字符串
     */
    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
