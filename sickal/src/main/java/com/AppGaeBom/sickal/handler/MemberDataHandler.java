package com.AppGaeBom.sickal.handler;

import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.dto.MemberDto;

import java.util.Optional;

public interface MemberDataHandler {
    Member join(MemberDto memberDto);
    void checkUserIdDuplication(MemberDto memberDto);
    Optional<Member> findById(String id);
    Member login(String id, String pw);

}
