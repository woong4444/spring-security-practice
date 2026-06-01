package com.jjang051.member.controller;

import com.jjang051.member.dto.MemberDto;
import com.jjang051.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(@ModelAttribute MemberDto memberDto, Model model) {
        //model.addAttribute("memberDto", new MemberDto());
        return "signup";
    }
    @PostMapping("/signup")
    public String signupProcess(@Valid @ModelAttribute MemberDto memberDto,
                                BindingResult bindingResult) {
        if(memberService.existsUserId(memberDto.getUserId())){
            bindingResult.rejectValue
            ("userId","duplicate","이미 사용중인 아이디입니다.");
        }
        if(memberService.existsEmail(memberDto.getEmail())){
            bindingResult.rejectValue
                    ("email","duplicate","이미 사용중인 이메일입니다.");
        }

        if(bindingResult.hasErrors()) {
            return "signup";
        }
        memberService.signup(memberDto);
        //System.out.println(memberDto.toString());
        return "redirect:/";
    }
}
