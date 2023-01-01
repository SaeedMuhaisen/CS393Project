package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.Entities.Location;
import com.SaeedAndEmre.CS393Project.Entities.Member;
import com.SaeedAndEmre.CS393Project.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member save(Member member) {
        try {
            Member result = memberRepository.save(member);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Member findById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            return members;
        }
    }
}
