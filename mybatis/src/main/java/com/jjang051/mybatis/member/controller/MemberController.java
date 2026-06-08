package com.jjang051.mybatis.member.controller;

import com.jjang051.mybatis.member.dto.MemberDto;
import com.jjang051.mybatis.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/signup")
    public String signup(){
        return "member/signup";
    }
    @PostMapping("/signup")
    public String signupProcess(MemberDto memberDto){
        memberService.signup(memberDto);
        return "member/signup";
    }
}
