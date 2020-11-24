package com.exp.main;

import com.exp.bean.People;
import com.exp.mapper.PeopleMapper;
import com.exp.server.PeopleServer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hhr
 * @date 2020-06-14 09:09
 * @description:
 */
@Service
public class Main {

    static PeopleMapper peopleMapper;
    static SimpleDateFormat dateFormat;
    static {
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        peopleMapper = (PeopleMapper) beanFactory.getBean("peopleMapper");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void doIt(){
        System.out.println(111);
    }

    @Test
    public void selectPeoPleOne(){
        System.out.println(peopleMapper.selectPeopleOne(2));
    }

    @Test
    public void insertPeoPleOne(){
        peopleMapper.insertPeopleOne(new People(0,"希奥利1","2018-9-9","1999-9-8 12:00:01"));
    }
}
