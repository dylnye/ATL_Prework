package com.galvanize.prework.demo.services;

import com.galvanize.prework.demo.entities.Member;
import com.galvanize.prework.demo.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Component
public class MemberService {

    @Autowired
    MemberRepository repository;

    public Member addMember(Member m) {
        return repository.save(m);
    }

    public List<Member> findByCity(String city) {
        return repository.findMembersByCity(city);
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Member getMember(long memberId) {
        return repository.findById(memberId).get();
    }

    public void update(Member member) {
        repository.save(member);
    }
}