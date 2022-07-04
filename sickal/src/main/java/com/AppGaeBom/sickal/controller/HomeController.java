package com.AppGaeBom.sickal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //Getmapping에 의해서 index.html로 가지 않고 바로 home으로감
    // 우선순위 mapping이 정적컨텐츠보다 위다 .

    @GetMapping("/")
    public String home(){
        return "home"; //home.html 호출
    }
}
