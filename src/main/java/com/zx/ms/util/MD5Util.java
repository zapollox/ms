package com.zx.ms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by DELL on 2018/5/6.
 */
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt="1a2b3c4d";

    //用户输入的密码提交到表单加密
    public static String inputPassToFormPass(String inputPass){
       String str = "" + salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
       return md5(str);
    }

    //表单密码提交到数据库加密
    public static String formPassToDBPass(String formPass,String salt){
        String str = "" + salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    //用户输入的密码提交到数据库加密
    public static String inputPassToDBPass(String input,String salt){
        String formPass = inputPassToFormPass(input);//明文密码转换成form密码
        String dbPass = formPassToDBPass(formPass, salt);//form密码转换成Db密码
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDBPass("123456","1a2b3c4d"));
    }
}
