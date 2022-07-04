package com.AppGaeBom.sickal.handler;

import com.AppGaeBom.sickal.dao.MemberDao;
import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberDataHandlerImpl implements MemberDataHandler{
    MemberDao memberDao;
    @Autowired
    public MemberDataHandlerImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    //빌더없이 하는것
    @Override
    public Member join(MemberDto memberDto) {
        Member member= new Member(memberDto.getId(),memberDto.getPassword(),memberDto.getName(),memberDto.getHeight(),memberDto.getBirth(),memberDto.getSex(),memberDto.getActivity(),memberDto.getGoal(),
                memberDto.getWeight());
//contro(String id ), - > service -> repose ->
        return memberDao.join(member);
    }
    public void checkUserIdDuplication(MemberDto memberDto){
        Member member= memberDto.toEntity();
        memberDao.checkUserIdDuplication(member);
    }

    @Override
    public Optional<Member> findById(String id) {
        return memberDao.findById(id);
    }
    //결국 DB에서 crud작업하는 건 Dao
    //하지만 Db를 건들 필요 없다? 서비스단에서 해결하자 !!!
    //복잡한 데이터 핸들링할 필요도 없다

}
