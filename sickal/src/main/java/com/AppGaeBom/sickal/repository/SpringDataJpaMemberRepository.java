package com.AppGaeBom.sickal.repository;

import com.AppGaeBom.sickal.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,String>, MemberRepository {

    //Member findById(String id);
    Optional<Member> findByName(String name);
    //List<Member> findAll();
    boolean existsById(String id);
    //boolean existsByName(String name);
    /*
    @Override
    default Optional<Member> findById(String id) {
        return Optional.empty();
    }

    @Override
    Optional<Member> findByName(String name);
    */

}
