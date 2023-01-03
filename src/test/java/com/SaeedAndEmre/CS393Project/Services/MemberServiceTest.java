package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.Entities.Member;
import com.SaeedAndEmre.CS393Project.Entities.Services;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @BeforeEach

    public void reset(){
        memberService.deleteAll();
    }
    @Test
    void save() {
        Member member=new Member();
        member.setName("Temp");
        member.setAddress("aaa");
        member.setEmail("abc@w.cn");
        member.setDrivingLicenseNumber("1234");
        member.setPhone("01231");
        member.setId(memberService.save(member).getId());

        Member result= memberService.findById(member.getId());

        assertEquals(member.getId(),result.getId());
        assertEquals(member.getAddress(),result.getAddress());
        assertEquals(member.getName(),result.getName());
        assertEquals(member.getEmail(),result.getEmail());
        assertEquals(member.getPhone(),result.getPhone());
        assertEquals(member.getDrivingLicenseNumber(),result.getDrivingLicenseNumber());

    }

    @Test
    void findById() {
        Member member=new Member();
        member.setName("Temp");
        member.setAddress("aaa");
        member.setEmail("abc@w.cn");
        member.setDrivingLicenseNumber("1234");
        member.setPhone("01231");
        member.setId(memberService.save(member).getId());
        Long id=member.getId();

        Member result= memberService.findById(id);
        assertEquals(member.getId(),result.getId());
        assertEquals(member.getAddress(),result.getAddress());
        assertEquals(member.getName(),result.getName());
        assertEquals(member.getEmail(),result.getEmail());
        assertEquals(member.getPhone(),result.getPhone());
        assertEquals(member.getDrivingLicenseNumber(),result.getDrivingLicenseNumber());



    }

    @Test
    void findAll() {
        Member member1=new Member();
        member1.setName("Temp");
        member1.setAddress("aaa");
        member1.setEmail("abc@w.cn");
        member1.setDrivingLicenseNumber("1234");
        member1.setPhone("01231");
        member1.setId(memberService.save(member1).getId());

        Member member2=new Member();
        member2.setName("Temp");
        member2.setAddress("aaa");
        member2.setEmail("abc@w.cn");
        member2.setDrivingLicenseNumber("1234");
        member2.setPhone("01231");
        member2.setId(memberService.save(member2).getId());

        List<Member> result= memberService.findAll();
        assertEquals(2,result.size());
        assertEquals(member1.getId(),result.get(0).getId());
        assertEquals(member1.getAddress(),result.get(0).getAddress());
        assertEquals(member1.getName(),result.get(0).getName());
        assertEquals(member1.getEmail(),result.get(0).getEmail());
        assertEquals(member1.getPhone(),result.get(0).getPhone());
        assertEquals(member1.getDrivingLicenseNumber(),result.get(0).getDrivingLicenseNumber());

        assertEquals(member2.getId(),result.get(1).getId());
        assertEquals(member2.getAddress(),result.get(1).getAddress());
        assertEquals(member2.getName(),result.get(1).getName());
        assertEquals(member2.getEmail(),result.get(1).getEmail());
        assertEquals(member2.getPhone(),result.get(1).getPhone());
        assertEquals(member2.getDrivingLicenseNumber(),result.get(1).getDrivingLicenseNumber());
    }
}