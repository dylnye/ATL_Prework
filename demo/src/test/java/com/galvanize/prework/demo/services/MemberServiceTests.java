package com.galvanize.prework.demo.services;

import com.galvanize.prework.demo.entities.Member;
import com.galvanize.prework.demo.repositories.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class MemberServiceTests {

    private List<Member> members = new ArrayList<>();

    @Autowired
    MemberService service;

    @Autowired
    MemberRepository repository;

    @Before
    public void setUp() throws Exception {
        Member member;
        for (int i = 0; i < 10; i++) {
            member = new Member();

            member.setFirstName("FirstName" + i);
            member.setLastName("LastName" + i);
            member.setCity("AnyCity" + i);
            member.setPhoneNumber("111-111-111" + i);

            members.add(member);
        }
        repository.saveAll(members);
    }

    @Test
    public void addMember() throws Exception {
        Member m = new Member();
        m.setFirstName("FirstName");
        m.setLastName("LastName");
        m.setCity("Atlanta");
        m.setPhoneNumber("770-555-5555");

        m = service.addMember(m);

        assertNotNull("member id was null after create", m.getMemberId());
    }

    @Test
    public void getMembersByCity() throws Exception {
        List<Member> members = service.findByCity("Marietta");
        assertNotNull(members);
        assertTrue("No members returned", members.size()>0);
        for(Member mem: members){
            assertTrue("Member "+mem.getMemberId()+"-"+mem.getLastName()+" was not from Marietta", mem.getCity().equals("Marietta"));
        }
    }

    @Test
    public void getAllMembers() throws Exception {
        List<Member> members = service.getAllMembers();
        assertNotNull(members);
        assertTrue(members.size()>0);
    }

    @Test
    public void getMemberById() throws Exception {
        Member actualMember = members.get(7);
        Member member = service.getMember(actualMember.getMemberId());
        assertNotNull(member);
        assertEquals(actualMember.getFirstName(), member.getFirstName());
        assertEquals(actualMember.getLastName(), member.getLastName());
        assertEquals(actualMember.getCity(), member.getCity());
        assertEquals(actualMember.getPhoneNumber(), member.getPhoneNumber());
    }

    @Test
    public void updateMemberPhoneNumber() throws Exception {
        String newNumber = "666-666-6666";
        Member member = members.get(5);
        member.setPhoneNumber(newNumber);
        service.update(member);

        Member newMem = service.getMember(member.getMemberId());
        assertNotNull(newMem);
        assertEquals(newNumber, newMem.getPhoneNumber());
    }
}