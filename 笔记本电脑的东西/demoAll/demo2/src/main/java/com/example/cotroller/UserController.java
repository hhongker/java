package com.example.cotroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author hhr
 * @date 2020-06-23 11:31
 * @description:
 */
@Controller
public class UserController {

//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//
//
//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
//        if("admin".equals(username) && "123456".equals(password)){
//            session.setAttribute("username",password);
//            return "redirect:index";
//        }
//        return "login";
//    }


    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @PutMapping("/test1")
    public String test1(){
        return "test1";
    }

}
