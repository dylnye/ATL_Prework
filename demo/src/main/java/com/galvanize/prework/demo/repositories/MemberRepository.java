package com.galvanize.prework.demo.repositories;

import com.galvanize.prework.demo.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    ArrayList<Member> findMembersByCity(String city);
}