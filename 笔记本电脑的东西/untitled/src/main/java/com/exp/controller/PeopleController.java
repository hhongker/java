package com.exp.controller;

import com.exp.bean.People;
import com.exp.server.PeopleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author hhr
 * @date 2020-06-14 10:17
 * @description:
 */
@Controller
public class PeopleController {

    @Autowired
    PeopleServer peopleServer;

    @RequestMapping("/selectPeopleOne")
    @ResponseBody
    public People selectPeopleOne(){
        return peopleServer.selectPeoPleOne(5);
    }


    @RequestMapping("/index")
    public String index(HttpServletRequest request){
//        People people = peopleServer.selectPeoPleOne(5);
//
//        request.getSession().setAttribute("login_name",people.getName());
//        request.setAttribute("people",people);
        request.setAttribute("peoples",peopleServer.selectPeoPleList());
        return "index";
    }


    /**
     * 文件上传功能
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file,HttpServletRequest request) throws IOException{
        String path = request.getSession().getServletContext().getRealPath("")+"//upload";

//        String path = "file/upload";
        System.out.println(path);
        String fileName = file.getOriginalFilename();
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        file.transferTo(dir);
        return "ok!";
    }
    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/down")
    public void down(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //模拟文件，myfile.txt为需要下载的文件
        String fileName = request.getSession().getServletContext().getRealPath("")+"//down//简历、面试和offer.txt";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = "下载文件.txt";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }


}
