package com.AppGaeBom.sickal.controller;


import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.domain.MemberActivity;
import com.AppGaeBom.sickal.domain.MemberGoal;
import com.AppGaeBom.sickal.domain.MemberSex;
import com.AppGaeBom.sickal.dto.InfoDto;
import com.AppGaeBom.sickal.dto.MemberDto;
import com.AppGaeBom.sickal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
    /// Controller 표시로 스프링에서 객체를 하나 생성해서 들고 있음
    /// -> 스프링커네이너에서 빈이 관리됌
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { //스프링빈에 등록되어있는 memberservice객체를 연결시켜줌 -> 의존성 주입
        this.memberService = memberService;
    }

    //같은 url이더라도 데이터 조회는 get, 등록은 Post
    @GetMapping("/members/new")
    public String createForm()
    {
        return "members/createMemberForm";
    }
    //객체로 받기
    //이게 진짜 json형식 MemberDto{name:"", id:""...}이런식으로 반환
    //
    @PostMapping("/members/new")
    public ResponseEntity<MemberDto> create(@Valid @RequestBody MemberDto memberDto){

        MemberDto response = memberService.join(memberDto);

        //가입끝냈으면 홈 화면 으로 보내기
        return ResponseEntity.status(HttpStatus.OK).body(response);
        //OR "redirect:/"
    }
    /* 회원가입 */
    //오류 형식 프론트에게 어떤식으로 넘길것인가?
    //객체형식으로 잘못된값을 그대로 넘길것인가?
    //아님 에러 메시지만 넘길것인가
    //이때 Errors는 반드시 Request 객체 바로 뒤에 위치해야 한다.
    //(두 개의 객체에 validation 검사를 한다면, 각각 객체 뒤에 Errors를 받도록 한다.)
    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid MemberDto memberDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("memberDto", memberDto);
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 다시 리턴 */
            return "/user/user-join";
        }
        memberService.checkUsernameDuplication(memberDto); //id중복 검증 에러시
        memberService.join(memberDto);  //

        return "redirect:/auth/login";
    }
    //main화면 기초대사량 권장섭취량 갖고오기4
    @GetMapping(value = "/main/{Id}")
    public InfoDto getProduct(@PathVariable String Id) {
         InfoDto infoDto = memberService.searchInfoById(Id);

        return infoDto;
    }




}
