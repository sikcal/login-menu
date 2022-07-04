package com.AppGaeBom.sickal.repository;

//이것도 repository로 스프링 빈에 등록

public class MemoryMemberRepository {//implements MemberRepository
    /*
    //json형식으로 저장하기 위해 map
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setMember_id(++sequence);
        store.put(member.getMember_id(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(
                member -> member.getMember_name().equals(name)
        ).findAny(); //하나라도 찾으면 반환해줌
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에있는 value들 다 반환
    }
    public void clearStore()
    {
        store.clear();
    }*/
}
