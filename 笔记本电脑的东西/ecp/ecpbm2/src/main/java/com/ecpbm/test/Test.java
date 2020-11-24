package com.ecpbm.test;

import com.ecpbm.bean.Admin;
import com.ecpbm.dao.AdminDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hhr
 * @date 2020-06-27 12:34
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpa = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AdminDao ad = (AdminDao) cpa.getBean("adminDao");
//        System.out.println(ad);
        System.out.println(ad.selectAdminOne("admin","123456"));
    }
}
