package com.AppGaeBom.sickal.dao;

import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.dto.InfoDto;
import com.AppGaeBom.sickal.dto.MemberDto;
import com.AppGaeBom.sickal.repository.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberDaoImpl implements MemberDao{
    //private final PasswordEncoder passwordEncoder;
    SpringDataJpaMemberRepository repository;
    @Autowired
    public MemberDaoImpl(SpringDataJpaMemberRepository repository) {
        this.repository = repository;
    }
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Member join(Member member) {
        System.out.println(" dao 까진 왔음>? ㄴㄴ 못왔음 ");
        System.out.println("멤버"+member);
        //member.setPassword();
        String encodedPassword =passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        Member member1 =  repository.save(member);
        System.out.println("멤버1"+member1);
        return member1;
    }
    @Transactional(readOnly = true)
    public void checkUserIdDuplication(Member member) {
        boolean useridDuplicate = repository.existsById(member.getId());
        if (useridDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    @Override
    public Optional<Member> findById(String Id) {
        Optional<Member> member = repository.findById(Id);
        return member;
    }

    @Override
    public Member login(String id, String pw) {
        return repository.findById(id)
                .filter(m -> m.getPassword().equals(pw))
                .orElse(null);
    }//
    /*
    생각해보니 pk는 id뿐 나머지는 중복되어도 상관없다?
    @Transactional(readOnly = true)
    public void checkNicknameDuplication(MemberDto dto) {
        boolean nicknameDuplicate = userRepository.existsByNickname(dto.toEntity().getNickname());
        if (nicknameDuplicate) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }
    */


}
