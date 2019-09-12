package com.imooc.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created By wangbo
 * 2019/9/9
 */
public class MD5Util {

    private static final String salt = "1a2b3c4d";

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassToFormPass(String inputPass) {
        String str = (salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4)).toString();
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = (salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4)).toString();
        return md5(str);
    }

    public static String inputPassToDBPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        return formPassToDBPass(formPass, saltDB);
    }

    public static void main(String[] args) {
//        System.out.println(inputPassToFormPass("123456"));
//        System.out.println(formPassToDBPass(inputPassToFormPass("123456"), salt));
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }
}
