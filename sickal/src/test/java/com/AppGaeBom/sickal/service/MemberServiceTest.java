package com.AppGaeBom.sickal.service;

class MemberServiceTest {

     /*
    MemberService memberService;
    MemoryMemberRepository memberRepository;// 선언만하고 beforeEach에서 만들어줌



    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //같은 repo를 쓰게 하기
        // 이것이 DI 외부에서 생성자 인자를 넣어줌
    }

    @AfterEach   //test한번끝날떄마다 repositorty 초기화  콜백메서드
    public void afterEach(){

        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setMember_name("kimseungwoo");
        //when
        Long saveId = memberService.join(member);

        //then
        //Optional<Member> one = memberService.findOne(saveId);
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getMember_name()).isEqualTo(findMember.getMember_name());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1= new Member();
        member1.setMember_name("kim");
        Member member2= new Member();
        member1.setMember_name("kim");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //memberService.join(member2); //에러 try catch로 잡을 수 있음 하지만
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }*/
}