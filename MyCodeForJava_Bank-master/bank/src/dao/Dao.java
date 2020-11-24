package dao;

import domain.Person;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Properties;

@SuppressWarnings("all")
public class Dao {

    String jdbc = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test3?user=root&password=";

    public Person require(String sql,String user) {
        Person person = null;
        try {
            Class.forName(jdbc);
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1,user);
            ResultSet rs = pstat.executeQuery();
            if(rs.next()){
                person = new Person(rs.getString(1),rs.getString(2),rs.getFloat(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    public int inser(Person person,String sql) {
        int count = -1;
        try {
            Class.forName(jdbc);
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1,person.getUser());
            pstat.setString(2,person.getPassword());
            pstat.setFloat(3,person.getMoney());
            count = pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int delete(String sql,String user) {
        int count = -1;
        try {
            Class.forName(jdbc);
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1,user);
            count = pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int update(String sql,String user,Float money) {
        int count = -1;
        try {
            Class.forName(jdbc);
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setFloat(1,money);
            pstat.setString(2,user);

            count = pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    //没有数据库
      public Person require(String user){
          Person person = null;
          try {
              Properties properties = new Properties();
              String url = "src/bank.properties";
              BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
              properties.load(bufferedReader);
              BufferedInputStream in = new BufferedInputStream(new FileInputStream(url));
              properties.load(new InputStreamReader(in, "utf-8"));
              String value = (String)properties.getProperty(user);
              String[] pwAndMoney = value.split(";");
              if(value != null){

                  person = new Person(user,pwAndMoney[0],Float.parseFloat(pwAndMoney[1]));
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return person;
      }

    private  Properties properties = new Properties();
    String u = "src/bank.properties";

    public Person requrePro(String name){
        Person person = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(u));
            properties.load(bufferedReader);
            String k = properties.getProperty(name==null?"":name);
            if(k != null){
                String[] kpm = k.split(";");
                person = new Person(name,kpm[0],Float.parseFloat(kpm[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }


    public boolean insertPro(Person person){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(u));
            properties.load(bufferedReader);
            OutputStream fos = new FileOutputStream(u);
            properties.setProperty(person.getUser(), person.getPassword()+";"+person.getMoney());//增加，改
            properties.store(fos, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deletePro(String user) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(u));
            properties.load(bufferedReader);
            OutputStream fos = new FileOutputStream(u);
            properties.remove(user);//删除
            properties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }
    public boolean updatePro(String user,Float mony) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(u));
            properties.load(bufferedReader);
            OutputStream fos = new FileOutputStream(u);
            properties.setProperty(user, this.requrePro(user).getPassword()+";"+mony);//增加，改
            properties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
