package com.example;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("abc"); //加密所需的salt(盐)
        String jiamiUsername = textEncryptor.encrypt("root");//要加密的数据（数据库的用户名或密码）
        String jiemiUsername = textEncryptor.decrypt("av1kGDkTXXl4ANH9s9MzRA==");

        System.out.println(jiamiUsername);
        System.out.println(jiemiUsername);
    }



}
