package controller;


import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.OneServices;

import java.util.Map;

@Controller
//@RestController
public class OneController {

    @Autowired
    OneServices oneServices;



    @RequestMapping(value = {"/index","/"},method = RequestMethod.GET)
    public String index(){
        System.out.println(1111);
        System.out.println(oneServices.oneServices1());
        return "redirect:index.jsp";
    }

    @RequestMapping(value = "/one1/{a}/{yy}",method = RequestMethod.GET)
    public String one1(@PathVariable(value = "a") int a,@PathVariable(name = "yy")String yy){
        System.out.println(2222);
        System.out.println(a);
        System.out.println(yy);
        return "one1";
    }


    @RequestMapping(value = "/one2",method = RequestMethod.GET)
    public String one2(Map map,String ok,String abc){
        System.out.println(2222);
        System.out.println(abc);
        System.out.println(ok);
        map.put("name","小明");
        map.put("age","12");
        return "one2";
    }

    @RequestMapping(value = "/one3",method = RequestMethod.GET)
    public String one3(Model model){
        System.out.println(3333);
        model.addAttribute("user",new User("小明",12));
        return "one3";
    }

    @RequestMapping(value = "/one4",method = RequestMethod.GET)
    public ModelAndView one4(){
        System.out.println(4444);
        ModelAndView model = new ModelAndView();
        model.setViewName("one4");
        model.addObject("user",new User("小明",12));
        return model;
    }

    @RequestMapping(value = "/one5",method = RequestMethod.GET)
   public String one5(ModelMap modelMap){
        System.out.println(5555);
        oneServices.oneServices1();
        modelMap.addAttribute("user",new User("小明",12));
        return "one5";
    }

    @ResponseBody
    @RequestMapping(value = "/one6",method = RequestMethod.GET)
    public User  one6(){
        System.out.println(6666);
        return new User("小明",12);
    }

}
