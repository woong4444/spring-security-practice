package com.jjang051.security.member.controller;

import com.jjang051.security.member.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;
    @GetMapping("/test-mail")
    @ResponseBody
    public String testMail() {
        mailService.sendMail("jjang051@hanmail.net","테스트입니다.","내용 없습니다.");
        return "success";
    }
    @GetMapping("/test-html-mail")
    @ResponseBody
    public String testHtmlMail() {
        mailService.sendMailHtml("jjang051@hanmail.net","테스트입니다.","<h1>안녕하세요. html로 메일을 보냅니다.</h1>");
        return "success";
    }
    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestParam String email) throws Exception {
        System.out.println("email==="+email);
        mailService.sendAuthCode(email);
        return "인증번호를 발송했습니다.";
    }

    @PostMapping("/verify")
    @ResponseBody
    public String verify(@RequestParam String email, @RequestParam String code) {
        boolean result = mailService.verifiedAuthCode(email,code);
        if(result){
            return "인증 완료";
        }
        return "인증 실패";
    }

}