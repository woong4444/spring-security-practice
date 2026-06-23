package com.jjang051.security.member.controller;


import com.jjang051.security.member.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @GetMapping("/test-mail")
    @ResponseBody
    public String testMail() {
        mailService.sendMail("kevin8899@naver.com","테스트입니다","내용 없습니다");
        return "success";
    }
    @GetMapping("/auth-member")
    @ResponseBody
    public String authMember() {
        mailService.sendAuthCode("kevin8899@naver.com");
        return "success";
    }
}
