package com.jjang051.security.member.controller;

import com.jjang051.security.member.dao.MemberDao;
import com.jjang051.security.member.dto.SignupDto;
import com.jjang051.security.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @PostMapping("/signup")
    public String signupProcess(@ModelAttribute SignupDto signupDto, RedirectAttributes redirectAttributes) {
        memberService.signup(signupDto);
        redirectAttributes.addFlashAttribute("toastMessage","회원가입이 되었습니다.");
        return "redirect:/member/login"; //링크
    }

    @GetMapping("/modify")
    public String modify() {
        return "member/modify";
    }
}
