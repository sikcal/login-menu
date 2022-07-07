package com.AppGaeBom.sickal.controller;

import com.AppGaeBom.sickal.dto.MemberDto;
import com.AppGaeBom.sickal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//contoller도 restful한 형식으로 만들기
// crud형식으로 분할
@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
    @Autowired
    MemberService memberService;

    @GetMapping("/getMember")
    public String getMember(MemberDto memberDto){
        return memberDto.toString();
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }
}
