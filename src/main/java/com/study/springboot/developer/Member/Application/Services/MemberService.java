package com.study.springboot.developer.Member.Application.Services;

import com.study.springboot.developer.Member.Domain.Entity.Member;
import com.study.springboot.developer.Member.Domain.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
