package com.AppGaeBom.sickal.repository;

public class JpaMemberRepository  {//implements MemberRepository
    //private final EntityManager em;
    /*

    public JpaMemberRepository(EntityManager em) {
        //jpa 사용하려면 엔티티 매니저 주입
        this.em = em;
    }

    @Override
    public Member save(Member member) {
       em.persist(member);
       return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.member_id = :member_id" , Member.class)
                .setParameter("member_id", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery(
                "select m from Member m", Member.class).getResultList();

    }*/
}
