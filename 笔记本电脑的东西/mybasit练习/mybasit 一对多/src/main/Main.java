package main;


import dao.StudentDao;
import domain.Person;
import domain.Stu;
import domain.Student2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import service.PersonService;


import service.StuAndCardService;
import service.StuAndClasService;
import util.MyFactory;
import util.MySpring;

import java.io.IOException;
import java.io.InputStream;

public class Main {
//    private static PersonService personService = MySpring.myObj("service.PersonService");
//
//    private static StuAndCardService stuAndCardService = MySpring.myObj("service.StuAndCardService");
//
//    private static StuAndClasService stuAndClasService = MySpring.myObj("service.StuAndClasService");


    public static void main(String[] args) {

//        StudentDao student = MyFactory.getSqlSession().getMapper(StudentDao.class);
        try {
            InputStream in = Resources.getResourceAsStream("configuration.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            StudentDao studentDao = sqlSessionFactory.openSession(true).getMapper(StudentDao.class);
            System.out.println(studentDao.selectStudentOne(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(student.selectStudentOne(1));

//        System.out.println(Main.personService.insertPerson(new Person("小花",19)));
//
//        System.out.println(Main.personService.deletePeron("3"));
//
//        System.out.println(Main.personService.updatePerson(new Person(2,"梨花",20)));
//
//        System.out.println(Main.personService.selectOnePerson("1"));
//
//        List<Person> personList = Main.personService.selectListPerson("desc");
//        for(Person person : personList){
//            System.out.println(person);
//        }




//
//        System.out.println(stuAndCardService.selectOneStu(2));
//
//        System.out.println(stuAndCardService.selectAllStu());
//
//        System.out.println(stuAndCardService.selectOneS(1));
//
//        System.out.println(stuAndCardService.selectAllS());
//
//        System.out.println(stuAndClasService.selectOneClas(1));
//
//        System.out.println(stuAndClasService.selectAllClas());
//
//        System.out.println(stuAndClasService.selectOneC(1));
//
//        System.out.println(stuAndClasService.selectAllC());

    }
}
