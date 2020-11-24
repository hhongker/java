package test;

import dao.StudentsDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hhr
 * @date 2020-07-05 14:40
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("springmvc.xml");

        StudentsDao studentsDao = (StudentsDao) cpxa.getBean("studentsDao");

        System.out.println(studentsDao.selectPeopleOne(1));

    }
}
