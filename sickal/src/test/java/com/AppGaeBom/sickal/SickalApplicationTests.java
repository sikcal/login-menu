package com.AppGaeBom.sickal;

import com.AppGaeBom.sickal.controller.MemberController;
import com.AppGaeBom.sickal.dao.MemberDao;
import com.AppGaeBom.sickal.domain.Member;
import com.AppGaeBom.sickal.domain.MemberActivity;
import com.AppGaeBom.sickal.domain.MemberGoal;
import com.AppGaeBom.sickal.domain.MemberSex;
import com.AppGaeBom.sickal.dto.InfoDto;
import com.AppGaeBom.sickal.dto.LoginDto;
import com.AppGaeBom.sickal.dto.MemberDto;
import com.AppGaeBom.sickal.repository.SpringDataJpaMemberRepository;
import com.AppGaeBom.sickal.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SickalApplicationTests {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	SpringDataJpaMemberRepository repository;
	@Autowired
	MemberService memberService;
	@Autowired
	MemberDao memberDao;

	@Test
	@DisplayName("암호화 테스트!")
	void passwordEncode(){
		//날것의 비번에서 암호화까지
		//given
		String rawPassword= "13413452";
		//when
		String encodedPassword = passwordEncoder.encode(rawPassword);
		//then
		assertAll(
				() -> assertNotEquals(rawPassword, encodedPassword),
				() -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
		); //암호화된값이 matches를 통해 맞는지 확인

	}

	@BeforeEach
	public void beforeEach(){
	//	repository = new SpringDataJpaMemberRepository();
//		memberService = new MemberService(repository); //같은 repo를 쓰게 하기
		// 이것이 DI 외부에서 생성자 인자를 넣어줌
	}


	@Test
	public void testConnection() {
		try {
			//oracle 접속 url
			String url ="jdbc:mysql://localhost:3306/sickal";
			//oracle 접속 계정
			String username = "root";
			//oracle 계정 패스워드
			String password = "1234";
			Connection conn = DriverManager.getConnection(url,username,password);

			System.out.println(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	@Test
	void 대사량계산(){
		InfoDto memberInfo=memberService.searchInfoById("ksw");
		System.out.println(memberInfo);
	}

	@Test
	void 삽입() {
		MemberDto member= new MemberDto(
		"ksw",
		"1234",
		"박의수",
		 180,
		 LocalDate.of(2000, 1, 1),
				MemberSex.MAN,
				MemberActivity.HARD,
				MemberGoal.GAIN,
				75
				);
		Member  member1 = member.toEntity();
		memberService.join(member);
		memberService.checkUsernameDuplication(member); //검증완료
		memberService.join(member);
		System.out.println("여기에요!!!"+repository.findAll());
		// member.setGoal(com.prob_jr.sikcal_app.domain.MemberGoal.GAIN);

	}
	@Autowired
	MemberController memberController;
	@Test
	void 컨트롤러(){
		LoginDto loginDto =new LoginDto("ksw","");
		//memberController.login(loginDto);

	}

}
