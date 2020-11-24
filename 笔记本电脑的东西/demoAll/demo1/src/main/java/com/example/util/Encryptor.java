package com.example.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author hhr
 * @date 2020-06-17 16:40
 * @description:
 */
public class Encryptor {
    static BasicTextEncryptor textEncryptor = null;
    static {
        textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("!@#$%");
    }

    public static String getJiaMi(String text){
        return textEncryptor.encrypt(text);
    }
    public static String getJieMi(String text){
        return textEncryptor.decrypt(text);
    }
}
