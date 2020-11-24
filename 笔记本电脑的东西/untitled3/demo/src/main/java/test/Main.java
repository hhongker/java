package test;

import com.example.controller.Controller1;
import com.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import test.tool.SpringUtil;


/**
 * @author hhr
 * @date 2020-07-05 15:26
 * @description:
 */
//@Component
public class Main {

//    @Autowired
//    private static StudentMapper studentMapper;

    public static void main(String[] args) {

//        ClassPathXmlApplicationContext cpa = new ClassPathXmlApplicationContext("application.properties");
//        StudentMapper studentMapper = (StudentMapper) cpa.getBean("studentMapper");

        Controller1 controller1 = (Controller1) SpringUtil.getBean("controller1");
        controller1.go();
    }

}
