package com.jjang051.security.member.controller;

import com.jjang051.security.member.dao.MemberDao;
import com.jjang051.security.member.dto.CustomUserDetails;
import com.jjang051.security.member.dto.SignupDto;
import com.jjang051.security.member.service.MailService;
import com.jjang051.security.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final MailService mailService;

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

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        model.addAttribute("loggedMember", customUserDetails.getMemberDto());
        return "member/info";
    }
    @GetMapping("/info02")
    public String info(Authentication authentication, Model model) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println(authentication);
        model.addAttribute("loggedMember", authentication.getPrincipal());
        return "member/info";
    }
    @GetMapping("/find-password")
    public String findPassword() {
        return "member/find-password";
    }
    @GetMapping("/reset-password")
    public String resetPassword() {
        return "member/reset-password";
    }
    @PostMapping("/reset-password")
    public String resetPasswordProcess(@RequestParam String newPassword, HttpSession session) {
        String email = (String) session.getAttribute("resetEmail");
        memberService.updatePassword(email , newPassword);
        return "redirect:/member/login";
    }
    @PostMapping("/find-password/send")
    @ResponseBody
    public String send(@RequestParam String email) throws Exception {
        System.out.println("email==="+email);
        mailService.sendResetPasswordCode(email);
        return "인증번호를 발송했습니다.";
    }
    @PostMapping("/find-password/verify")
    @ResponseBody
    public String verify(@RequestParam String email, @RequestParam String code, HttpSession session) {
        boolean result = mailService.verifiedAuthCode(email,code);
        if(result){
            session.setAttribute("resetEmail",email);
            return "인증 완료";
        }
        return "인증 실패";
    }

//    @PostMapping("/logout")
//    public String logout() {
//        return "redirect:/";
//    }
}