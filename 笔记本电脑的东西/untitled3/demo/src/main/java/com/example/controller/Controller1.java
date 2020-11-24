package com.example.controller;

import com.example.bean.Students;
import com.example.mapper.StudentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author hhr
 * @date 2020-07-04 14:44
 * @description:
 */
@Controller
//@MapperScan("com.example.mapper")
public class Controller1 {


    public void go(){
        System.out.println(111);
    }


    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/studentOne")
    @ResponseBody
    public Students selectStudentOne(){

        return studentMapper.selectPeopleOne(1);
    }




}
