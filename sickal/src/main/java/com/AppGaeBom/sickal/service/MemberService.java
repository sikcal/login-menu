package com.AppGaeBom.sickal.service;

import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.dto.InfoDto;
import com.AppGaeBom.sickal.dto.LoginDto;
import com.AppGaeBom.sickal.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Map;

@Service
public interface MemberService {
    MemberDto join(MemberDto memberDto);
    Map<String, String> validateHandling(Errors errors);
    void checkUsernameDuplication(MemberDto memberDto);
    InfoDto searchInfoById(String id);
    MemberDto login(String id, String pw);
}
