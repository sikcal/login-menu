package com.AppGaeBom.sickal.controller;

import com.AppGaeBom.sickal.dto.InfoDto;
import com.AppGaeBom.sickal.dto.MemberDto;
import com.AppGaeBom.sickal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/main-api")
@SessionAttributes("memberDto")
public class MainController {

    //Getmapping에 의해서 index.html로 가지 않고 바로 home으로감
    // 우선순위 mapping이 정적컨텐츠보다 위다 .

    @Autowired
    MemberService memberService;

    public MainController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ModelAttribute("memberDto")
    public MemberDto setMemberDto(){
        return new MemberDto();
    }
    //홈화면 구현
    //회원정보를 통해 세션통과가 되었다면 대사량 정보 갖고옴
    //main화면 기초대사량 권장섭취량 갖고오기4
    //main하면 불러오는방법 1 프론트에서 받기
    @GetMapping(value = "/main/{Id}")
    public InfoDto getProduct(@PathVariable String Id) {
        InfoDto infoDto = memberService.searchInfoById(Id);

        return infoDto;
    }
    //위에는 만약 세션이 유지 안되서 프론트에서 id값 받을때
    //main하면 불러오는방법 2 로그인 정보를 토대로 session에 저장해두고
    // 로그인 후 바로 main문으로
    @RequestMapping("/main")
    public InfoDto getMainInfo(@ModelAttribute("memberDto") MemberDto memberDto){
        InfoDto infoDto = memberService.searchInfoById(memberDto.getId());
        //model로 넘기는것보다 그냥 객체로 return하는게 낫지 않음?
        //model로 넘기면 타입 String으로 해줘야함'
        //여기다가 정훈님이 구현한 코드까지 해서 넘기기
        return infoDto;
    }

}
