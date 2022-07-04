package com.AppGaeBom.sickal.service;

import com.AppGaeBom.sickal.dao.MemberDao;
import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.domain.MemberActivity;
import com.AppGaeBom.sickal.domain.MemberSex;
import com.AppGaeBom.sickal.dto.InfoDto;
import com.AppGaeBom.sickal.dto.MemberDto;
import com.AppGaeBom.sickal.handler.MemberDataHandler;
import com.AppGaeBom.sickal.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//Service로 스프링 빈에 등록하기 그래야 autowired됌
//@Transactional 항상 롤백 시키는
@Service
public class MemberServiceImpl implements MemberService {
    MemberDataHandler memberDataHandler;
    //굳이 엔티티로 바꿔서 연산할 필요없는것들은
    // data 핸들러 건너뛰고 바로 dao 로 dto 형식으로 리턴받기


    @Autowired
    public MemberServiceImpl(MemberDataHandler memberDataHandler) {
        this.memberDataHandler = memberDataHandler;
    }



    /**
     * 회원가입
     */
    public MemberDto join(MemberDto memberDto){
        //중복 아이디ㅣ x
        //validateDuplicateMember(member); // 중복회원 검증 메서드
        checkUsernameDuplication(memberDto);
        Member member = memberDataHandler.join(memberDto);
        return memberDto;
    }
    //정규화 패턴 맞는지 확인하기
    //이정도는 서비스단에서 해결 디비 뒤질 필요없잖아
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());        }
        return validatorResult;
    }
    /* 아이디 중복 여부 확인 */
    @Transactional(readOnly = true)
    public void checkUsernameDuplication(MemberDto dto) {
        memberDataHandler.checkUserIdDuplication(dto);

    }
    //멤버 정보 갖고와서 기초대사량 등등 계산허기
   @Override
    public InfoDto searchInfoById(String id) {
        Optional<Member> member =memberDataHandler.findById(id);
        Member member1 = member.get();
        int age = calculateAge(member1.getBirth());
        double BMR= CalculateBMR(member1.getWeight(),member1.getHeight(),age,member1.getSex());
        double rate =CalculateActivity(member1.getActivity(),BMR);
        int suggestion=0;
        double[]ratio= new double[3];
        switch (member1.getGoal()){
            case GAIN:{
                suggestion=(int)rate+500;
                ratio[0]=0.5;ratio[1]=0.2;ratio[2]=0.3;break;
            }
            case LOSE:{
                suggestion=(int)rate-500;
                ratio[0]=0.3;ratio[1]=0.5;ratio[2]=0.2; break;
            }
            case REMAIN:{
                suggestion=(int)rate;
                ratio[0]=0.4;ratio[1]=0.4;ratio[2]=0.2;break;
            }
        }
       InfoDto infoDto = new InfoDto((int)(suggestion*ratio[0]),(int)(suggestion*ratio[1]), (int)(suggestion*ratio[2]),(suggestion));

       return infoDto;
    }
    public double CalculateBMR(int weight, int height, int age,MemberSex sex){
        double BMR=((10*weight)+(6.25*height)-(5*age));
        switch (sex){
            case MAN:
            {
                BMR=BMR+5; break;
            }
            case WOMAN:
            {
                BMR=BMR-161; break;
            }
        }
        return BMR;
    }
    public double CalculateActivity(MemberActivity targetActivity, double BMR){
        double rate=0.0;
        switch (targetActivity){
            case LIGHT:{
                rate=BMR*1.375; break;
            }
            case NORMAL:{
                rate=BMR*1.55; break;
            }
            case HARD:{
                rate=BMR*1.725; break;
            }
            case SUPER:{
                rate=BMR*1.9; break;
            }
        }
        return rate;
    }

    public int calculateAge(LocalDate birth ){
        String birth1 = birth.toString();
        LocalDate now = LocalDate.now();
        LocalDate parsedBirthDate = LocalDate.parse(birth1, DateTimeFormatter.ofPattern("yyyyMMdd"));

        int age = now.minusYears(parsedBirthDate.getYear()).getYear(); // (1)

        // (2)
        // 생일이 지났는지 여부를 판단하기 위해 (1)을 입력받은 생년월일의 연도에 더한다.
        // 연도가 같아짐으로 생년월일만 판단할 수 있다!
        if (parsedBirthDate.plusYears(age).isAfter(now)) {
            age = age -1;
        }

        return age;
    }



/*
    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getMember_id()).ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다. ");
        });
    }*/

    /**
     * 전체 회원 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


*/
}
