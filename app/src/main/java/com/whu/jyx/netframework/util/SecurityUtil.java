package com.whu.jyx.netframework.util;

import android.util.Base64;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 * 加密工具类
 *
 * @author Liang.WW
 * @date 2017-01-06
 */

public class SecurityUtil {

    //对字符串进行MD5加密
    public static String MD5encode(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    //对Map字符串中的每一个值进行Base64加密
    public static Map<String,String> Base64EncodeMap(Map<String,String> map){
        Map<String,String> result = new TreeMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.put(entry.getKey(),Base64.encodeToString(entry.getValue().getBytes(),Base64.NO_WRAP));
        }
        return result;
    }


}
