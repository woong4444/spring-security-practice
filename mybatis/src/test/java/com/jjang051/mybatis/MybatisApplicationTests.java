package com.jjang051.mybatis;

import com.jjang051.mybatis.member.dao.MemberDao;
import com.jjang051.mybatis.member.dto.LoginDto;
import com.jjang051.mybatis.member.dto.MemberDto;
import com.jjang051.mybatis.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class MybatisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp(){
        System.out.println("테스트 시작");
    }
    @AfterEach
    void afterEach(){
        System.out.println("테스트 끝");
    }

    @Test
    void 회원가입테스트() {
        System.out.println("회원가입 테스트 시작");
        MemberDto memberDto = MemberDto.builder()
                .userId("jjang052")
                .userName("장동건")
                .userPw("1234")
                .zipcode(11111)
                .phone("01011111111")
                .email("jjang053@hanmail.net")
                .address("장항동")
                .detailAddress("111-111")
                .thumbnailProfile("fdsfdsfd")
                .profile("fdsfdsf")
                .regDate(LocalDateTime.now())
                .build();
        int result = memberService.signup(memberDto);
        Assertions.assertEquals(1, result);
    }

    @Test
    void 로그인성공테스트() {
        LoginDto loginDto = LoginDto.builder()
                                    .userId("jjang051")
                                    .userPw("1234")
                            .build();
        MemberDto memberDto = memberService.login(loginDto);
        Assertions.assertEquals("장성호", memberDto.getUserName());

    }
    @Test
    void 아이디중복테스트() {
        int result = memberService.idCheck("aaaa");
        Assertions.assertEquals(1, result);
    }

    @Test
    void 회원탈퇴테스트() {
        LoginDto loginDto = LoginDto.builder()
                .userId("aaaa")
                .userPw("1111")
                .build();
        int result = memberService.deleteMember(loginDto);
        Assertions.assertEquals(1, result);
    }
}
