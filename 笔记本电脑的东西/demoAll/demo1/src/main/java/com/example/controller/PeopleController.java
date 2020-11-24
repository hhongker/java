package com.example.controller;

import com.example.bean.People;

import com.example.bean.VegetablesBean;
import com.example.config.PeopleConfig;
import com.example.config.VegetablesConfig;
import com.example.repository.PeopleRepository;

import java.net.http.HttpRequest;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author hhr
 * @date 2020-06-17 13:32
 * @description:
 */
@Controller
public class PeopleController {

    @Autowired
    PeopleConfig peopleConfig;
    @Autowired
    VegetablesConfig vegetablesConfig;

    @Autowired
    PeopleRepository peopleRepository;

    @Value("${people.name}")
    private String name;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello spring-boot";
    }



    @GetMapping("/peopleConfig")
    @ResponseBody
    public People people(){
        return new People(peopleConfig.getId(),peopleConfig.getName(),peopleConfig.getBirthday(),peopleConfig.getCreateTime());
//        return peopleConfig;
    }


    @GetMapping("/vegetables")
    @ResponseBody
    public VegetablesBean vegetables(){
        return new VegetablesBean(vegetablesConfig.getId(),vegetablesConfig.getName(),vegetablesConfig.getBirthday(),vegetablesConfig.getCreateTime());
    }

    @GetMapping("/name")
    @ResponseBody
    public String name(){
        return name;
    }


    @GetMapping({"/","/index"})
    public String index(HttpServletRequest request){
        request.setAttribute("vegetables",new VegetablesBean(vegetablesConfig.getId(),vegetablesConfig.getName(),vegetablesConfig.getBirthday(),vegetablesConfig.getCreateTime()));

        return "index";
    }

    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }


    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }
    @PostMapping("/tijiao")
    public String tijiao(People people, @RequestParam("name") String nae){
        System.out.println(people);
        System.out.println(nae);
        return "redirect:/test2";
    }
    @GetMapping("/test3")
    public ModelAndView test3(){

        List<People> list = peopleRepository.findAll();
        ModelAndView modelAndView1 = new ModelAndView();
        modelAndView1.addObject("list",list);
        modelAndView1.addObject("list1",list);
        modelAndView1.setViewName("test3");
        System.out.println(modelAndView1.getModel());
        System.out.println(modelAndView1.getModelMap());
        System.out.println(modelAndView1.getView());
        modelAndView1.addObject("date",new Date());

        return modelAndView1;
    }

    @GetMapping("/test4")
    public String test4(){
        return "test4";
    }

    @GetMapping("/test5")
    public String test5() {
        return "test5";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session){
        if("adm".equals(name) && "123".equals(password)) {
            session.setAttribute("name",name);
            return "redirect:index";
        }else {
            return "login";
        }
    }





}
