package com.jjang051.security.member.controller;

import com.jjang051.security.member.dao.MemberDao;
import com.jjang051.security.member.dto.SignupDto;
import com.jjang051.security.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup() {
        System.out.println("signup");
        return "member/signup";
    }
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/signup")
    public String signupProcess(@ModelAttribute SignupDto signupDto) {
        memberService.signup(signupDto);
        return "redirect:/member/login";
    }

    @GetMapping("/modify")
    public String modify() {
        return "member/modify";
    }
}
