package com.study.springboot.developer.Member.UserInterface.Controller;

import com.study.springboot.developer.Member.Application.Services.MemberService;
import com.study.springboot.developer.Member.Domain.Entity.Member;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService; // 실제 서비스 대신 모킹된 객체를 사용

    @Test
    public void testFindAllMembers() throws Exception {
        // Given
        List<Member> members = Arrays.asList(new Member(1L, "Yosub"), new Member(2L, "Hyonsub"));
        Mockito.when(memberService.getAllMembers()).thenReturn(members);

        // When & Then
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Yosub"))
                .andExpect(jsonPath("$[1].name").value("Hyonsub"));
    }
}