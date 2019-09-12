package com.galvanize.prework.demo.controllers;

import com.galvanize.prework.demo.entities.Member;
import com.galvanize.prework.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberRestController {

    @Autowired
    MemberService service;

    @PostMapping
    public Member addMember(@RequestBody Member member){
        return service.addMember(member);
    }

    @GetMapping
    public List<Member> findMembersByCity(@RequestParam String city){
        return service.findByCity(city);
    }

    @GetMapping("/all")
    public List<Member> findAllMembers(){
        return service.getAllMembers();
    }

    @GetMapping("/{memId}")
    public Member findById(@PathVariable Long memId){
        return service.getMember(memId);
    }
}