package service;

import dao.StuAndCardDao;
import domain.Student;
import util.MySpring;

import java.util.ArrayList;
import java.util.List;

public class StuAndCardService {

    StuAndCardDao stuAndCardDao = MySpring.myObj("dao.StuAndCardDao");

    public Student selectOneStu(int id){
        return stuAndCardDao.selectOneStu(id);
    }

    public List<Student> selectAllStu(){
        return stuAndCardDao.selectAllStu();
    }

    public Student selectOneS(int id){
        return stuAndCardDao.selectOneS(id);
    }

    public List<Student> selectAllS(){
        return stuAndCardDao.selectAllS();
    }
}
