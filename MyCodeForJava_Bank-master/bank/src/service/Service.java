package service;

import dao.Dao;
import domain.Person;
import util.MySpring;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class Service {

    private Dao dao = MySpring.obj("dao.Dao");

    //有数据库
//    public Person requre(String user) {
//        return dao.require("select * from bank where name = ?",user);
////        return dao.require(user);
//    }

    public boolean login(String user, String password) {
//        Person person = this.requre(user);有数据库
        Person person = this.requrePro(user);//没有数据库
        if(person != null && person.getPassword().equals(password)){
            return true;
        }
        return false;
    }


//    public boolean insert(Person person) {
//        if(dao.inser(person,"insert into bank values(?,?,?)") != -1){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean delete(String user) {
//       if(dao.delete("delete from bank where name = ?",user) != -1){
//           return true;
//       }
//        return false;
//    }
//
//    public boolean update(String user,Float mony) {
//        if(dao.update("update bank set money = ? where name = ?",user,mony) != -1){
//            return true;
//        }
//        return false;
//    }


    //没有数据库===================================
    public Person requrePro(String name){
        return dao.requrePro(name);
    }

    public boolean insertPro(Person person){
        return dao.insertPro(person);
    }
    public boolean deletePro(String user) {
        return dao.deletePro(user);
    }
    public boolean updatePro(String user,Float mony) {
        return dao.updatePro(user,mony);
    }


}
