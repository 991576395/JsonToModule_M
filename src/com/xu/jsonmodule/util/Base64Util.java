package com.xu.jsonmodule.util;

import org.apache.commons.net.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * base64编码工具类
 * @author ddr
 * @version 2013-11-29
 */
public class Base64Util {

    /**
     *  将 s 进行 BASE64 编码
     * @param s
     * @return
     */
    public static String encode(String s) {
        
        if (s == null)
            return null;
        try {
            return  Base64.encodeBase64URLSafeString(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**将 BASE64 编码的字符串 s 进行解码
     * 
     * @param s
     * @return
     */
    public static byte[] decode(String s) {
        if (s == null)
            return null;
        try {
            byte[] b = Base64.decodeBase64(s.getBytes());
            return b;
        } catch (Exception e) {
            return null;
        }
    }


}