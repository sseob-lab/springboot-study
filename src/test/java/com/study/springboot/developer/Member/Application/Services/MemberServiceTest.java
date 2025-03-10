package com.study.springboot.developer.Member.Application.Services;

import com.study.springboot.developer.Member.Domain.Entity.Member;
import com.study.springboot.developer.Member.Domain.Repositories.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MemberServiceTest {
    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void testFindAllMembers() {
        // Given mock데이터준비
        List<Member> members = Arrays.asList(
            new Member(1L, "shim yosub"),
            new Member(2L, "shim hyonsub")
        );

        // When
        // findAll 호출 시 위에서 정의해둔 mock 데이터가 반환되도록 설정
        Mockito.when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("shim yosub", result.get(0).getName());
        Assertions.assertEquals("shim hyonsub", result.get(1).getName());
    }
}