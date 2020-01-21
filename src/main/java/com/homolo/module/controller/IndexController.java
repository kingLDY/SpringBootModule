package com.homolo.module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    //这是返回json格式代码
    //@ResponseBody
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
