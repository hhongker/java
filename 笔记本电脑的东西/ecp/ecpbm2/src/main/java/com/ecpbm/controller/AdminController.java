package com.ecpbm.controller;

import com.ecpbm.server.AdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


/**
 * @author hhr
 * @date 2020-06-20 11:15
 * @description:
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes(value = { "admin" })
public class AdminController {

    @Autowired
    AdminServer adminServer;

    @ResponseBody
    @RequestMapping(value = {"/toLogin"},method={RequestMethod.POST,RequestMethod.GET})
    public String toLogin(@RequestParam("username") String username, @RequestParam("pwd") String pwd){
        if(adminServer.selectAdminOne(username,pwd) != null) return "ok";
        return "用户名或密码错误";
    }
}
