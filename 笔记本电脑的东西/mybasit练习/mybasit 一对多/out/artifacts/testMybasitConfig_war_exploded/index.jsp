<%@ page import="service.PersonService" %>
<%@ page import="util.MySpring" %>
<%@ page import="service.StuAndCardService" %>
<%@ page import="service.StuAndClasService" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/11/14
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$

  <%
     PersonService personService = MySpring.myObj("service.PersonService");

     StuAndCardService stuAndCardService = MySpring.myObj("service.StuAndCardService");

     StuAndClasService stuAndClasService = MySpring.myObj("service.StuAndClasService");



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
        System.out.println(stuAndClasService.selectAllC());

  %>
  </body>
</html>
