package com.study.springboot.developer.Member.UserInterface.Controller;

import com.study.springboot.developer.Member.Domain.Entity.Member;
import com.study.springboot.developer.Member.Application.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService testService;

    @GetMapping("/test")
    public List<Member> test() {
        return testService.getAllMembers();
    }
}
