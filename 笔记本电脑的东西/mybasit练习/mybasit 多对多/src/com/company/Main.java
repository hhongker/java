package com.company;

import domain.Student;
import domain.Teacher;
import org.apache.ibatis.annotations.Param;
import uitl.GetSession;

import java.util.List;

public class Main {



    private static Student selectStudentOne(int id){
        return GetSession.getSqlSessionFactory().selectOne("selectStudentOne", id);
    }

    private static List<Student> selectStudentAll(){
        return GetSession.getSqlSessionFactory().selectList("selectStudentAll");
    }

    private static Teacher selectTeacherOne(int id){
        return GetSession.getSqlSessionFactory().selectOne("selectTeacherOne", id);
    }

    private static List<Teacher> selectTeacherAll(){
        return GetSession.getSqlSessionFactory().selectList("selectTeacherAll");
    }


    public static void main(String[] args) {
//        System.out.println(selectStudentOne(1));
//
//        System.out.println(selectStudentAll());
//
//        System.out.println(selectTeacherOne(1));

        System.out.println(selectStudentAll());

    }
}
