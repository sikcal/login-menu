package com.AppGaeBom.sickal.controller;


import com.AppGaeBom.sickal.component.SessionManager;
import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.dto.LoginDto;
import com.AppGaeBom.sickal.dto.MemberDto;
import com.AppGaeBom.sickal.exception.Constants;
import com.AppGaeBom.sickal.exception.SickalException;
import com.AppGaeBom.sickal.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

//로그인 세션 유지하기
//Model객체에 memberDto라는 이름으로 저장된 데이터를 자동으로 세션에 저장
//로그인 폼에서 입력받은 커맨드 객체를 서비스 객체(memberService)를 통해 확인하여 유효한다면
//model객체에 조회한 데이터를 담고 유효하지 않다면다시 로그인페이지로 리다이렉트
// login 함수
@SessionAttributes("memberDto")
@RestController
@RequestMapping("/api/v1/member-api")
public class MemberController {
    /// Controller 표시로 스프링에서 객체를 하나 생성해서 들고 있음
    /// -> 스프링커네이너에서 빈이 관리됌
    private final MemberService memberService;
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public MemberController(MemberService memberService, SessionManager sessionManager) { //스프링빈에 등록되어있는 memberservice객체를 연결시켜줌 -> 의존성 주입
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto")LoginDto loginDto){
        return "login/loginForm";
    }
    @PostMapping("/login")
    public ResponseEntity<MemberDto> login(@Valid @ModelAttribute("memberDto") LoginDto loginDto, BindingResult bindingResult,Model model) throws SickalException{
        if(bindingResult.hasErrors()){ // binding result valid의 형식에 맞지않으면 그에 맞ㄴ느 오류 생성됌
            //밑에 binding.reject를 사용해서 글로벌 오류 objectError생성
            throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.BAD_REQUEST, "문제발생11");
        }
        MemberDto findMember = memberService.login(loginDto.getId(),loginDto.getPw());
        LOGGER.info("login정보: {}",findMember);
        if(findMember==null){
            //bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 맞지 않습니다.");
        }
        //세션에 저장
        model.addAttribute("memberDto", findMember);
        return ResponseEntity.status(HttpStatus.OK).body(findMember);
        //throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.OK,"로그인 성공");
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
    //bindingresult를 써서 valid에 실패한 부분((MemberDto형식에 맞지않은)의 메세지를 보냄
    @PostMapping("/join")
    public  ResponseEntity<MemberDto>  joinProc(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult, Model model) throws SickalException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            //model에 추가하는것은 session까지 저장됌
            model.addAttribute("memberDto", memberDto);
            /* 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }*/
            System.out.println("여기여여이격");
            //error throw
            //throw  new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.BAD_REQUEST, "");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(memberDto);
        }
        try {
            memberService.checkUsernameDuplication(memberDto); //id중복 검증 에러시
            memberService.join(memberDto);  //
        }
        catch (Exception e){
            throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.NOT_ACCEPTABLE, "중복된 id가 있습니다.");
        }

        throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.OK, "회원가입 성공 두 단계 다 통과함 ㅋ");
    }
    // 로그아웃시 session에서 제거해줘야함
    @GetMapping("/member/logout")
    public void logout(SessionStatus status) throws SickalException{
        //로그인 되있을때만 정상 로그아웃됌
        if(!status.isComplete()){
            status.setComplete();
        }
        else{
            throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.BAD_REQUEST,"로그인부터 하고 로그아웃 누르셈 ㅋ");
        }
    }

    // custom exception만든거 test해보깅
    @PostMapping("member/exception")
    public void exceptionTest() throws SickalException{
        throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.BAD_REQUEST, "승우가 만든 ERROr");
    }





}
