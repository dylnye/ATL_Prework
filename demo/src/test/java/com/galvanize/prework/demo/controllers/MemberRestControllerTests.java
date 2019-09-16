package com.galvanize.prework.demo.controllers;

import com.galvanize.prework.demo.entities.Member;
import com.galvanize.prework.demo.repositories.MemberRepository;
import com.galvanize.prework.demo.services.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MemberRestControllerTests {

    private String BASE_URI = "/";

    @Autowired
    MockMvc mvc;

    @Autowired
    MemberService service;

    @Autowired
    MemberRepository repository;

    List<Member> members = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Member member = null;
        for (int i = 0; i < 10; i++) {
            member = new Member();

            member.setFirstName("FirstName" + i);
            member.setLastName("LastName" + i);
            member.setCity(i % 2 == 0 ? "Marietta" : "Atlanta");
            member.setPhoneNumber("111-111-111" + i);

            repository.save(member);

            members.add(member);
        }

        //repository.saveAll(members);
    }

    /*@Test
    public void addMember() throws Exception {
        MockHttpServletRequestBuilder request = post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").exists());
    }*/

    @Test
    public void getMembersByCity() throws Exception {
        mvc.perform(get("/?city=Marietta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city", is("Marietta")))
                .andExpect(jsonPath("$[3].city", is("Marietta")));
    }

    @Test
    public void getAllMembers() throws Exception {
        mvc.perform(get("/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void getMemberById() throws Exception {
        Member member = members.get(5);
        mvc.perform(get("/" + member.getMemberId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId", is(member.getMemberId().intValue())));
    }

    /*@Test
    public void updateMemberPhoneNumber() throws Exception {
        Member member = members.get(2);
        String newNumber = "888.888.8888";

        String json = String.format("{ \"memberId\":\"%s\",\"phoneNumber\":\"$s\" }", member.getMemberId(), newNumber);

        MockHttpServletRequestBuilder request = post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber", is(newNumber)));
    }*/

    private String getJson(){
        StringBuffer buff = new StringBuffer();
        buff.append("{\n");

        buff.append("\"firstName\": \"theFirstName\", \n");
        buff.append("\"lastName\": \"theLastName\",\n");
        buff.append("\"city\": \"theCity\",\n");
        buff.append("\"phoneNumber\": \"123-456-7890\",\n");

        buff.append("}");

        return buff.toString();
    }
}