package com.AppGaeBom.sickal.dao;

import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.dto.InfoDto;
import com.AppGaeBom.sickal.dto.MemberDto;

import java.util.Optional;


public interface MemberDao {
    Member join(Member member);
    void checkUserIdDuplication(Member member);
    Optional<Member> findById(String Id);
    Member login(String id, String pw);
}
