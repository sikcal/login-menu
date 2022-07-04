package com.AppGaeBom.sickal.repository;

public class MemoryMemberRepositoryTest {
    /*
    MemoryMemberRepository repository =new MemoryMemberRepository();

    @AfterEach   //test한번끝날떄마다 repositorty 초기화  콜백메서드
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setMember_name("kimseungwoo1234");
        repository.save(member); //회원저장


        Member result = repository.findById(member.getMember_id()).get();
        //System.out.println("result ="+ (result==member));  초보자 방법
        //Assertions.assertEquals(member,result);
        Assertions.assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member = new Member();
        member.setMember_name("kimseungwoo1234");
        repository.save(member); //회원저장
        Member member2 = new Member();
        member2.setMember_name("seungwoo1234");
        repository.save(member2); //회원저장

        Member result = repository.findByName("kimseungwoo1234").get();

        Assertions.assertThat(result).isEqualTo(member);
    }
    @Test
    public void findAll(){
        Member member = new Member();
        member.setMember_name("kimseungwoo1234");
        repository.save(member); //회원저장
        Member member2 = new Member();
        member2.setMember_name("seungwoo1234");
        repository.save(member2); //회원저장

       List<Member> result= repository.findAll();
       Assertions.assertThat(result.size()).isEqualTo(2);
    }*/
}
