package com.jjang051.security.member.controller;

import com.jjang051.security.member.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;

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
    @ResponseBody
    public String signupProcess(@ModelAttribute SignupDto signupDto) {
        System.out.println("signupProcess");
        SignupDto encodedSignupDto = SignupDto.builder()
                .userId(signupDto.getUserId())
                .userName(signupDto.getUserName())
                .userPw(passwordEncoder.encode(signupDto.getUserPw()))
                .build();
        System.out.println(encodedSignupDto.getUserPw()+" / "+signupDto.getUserPw());
        return "redirect:/member/login";
    }

    @GetMapping("/modify")
    public String modify() {
        return "member/modify";
    }
}
