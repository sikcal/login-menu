package com.AppGaeBom.sickal.repository;

import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.dto.InfoDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //Member save(Member member);
    //이건 infoDto형식으롭 ㅏㄷ아야함으로 쿼리문 새로짜서 집계함수형태로 만들기
    //Member findById(String id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
