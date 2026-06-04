package com.jjang051.member.controller;

import com.jjang051.member.dto.LoginDto;
import com.jjang051.member.dto.MemberDto;
import com.jjang051.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        //비밀번화 확인 검증
        if(!memberDto.getUserPw().equals(memberDto.getUserPwCheck())) {
            bindingResult.rejectValue
                    ("userPwCheck","passwordMismatch","비밀번호가 일치하지 않습니다.");
        }
        if(bindingResult.hasErrors()) {
            return "signup";
        }
        memberService.signup(memberDto);
        //System.out.println(memberDto.toString());
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto) {
        return "login";
    }
    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute LoginDto loginDto,
                               BindingResult bindingResult,
                               HttpSession httpSession
                               ) {

        if(bindingResult.hasErrors()) {
            return "login";
        }
        boolean result = memberService.loginCheck(loginDto);
        if(!result) {
            bindingResult.reject("loginFail","까꿍 아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }
        MemberDto loggedMemberDto = memberService.getMemberInfo(loginDto.getUserId());
        //이름
        httpSession.setAttribute("loggedMemberDto",loggedMemberDto); //aaa,장성호,12133 //bbb, 장동건,32111
        return "redirect:/";
    }
    @GetMapping("/id-check")
    @ResponseBody
    public String idCheck(@RequestParam(name="userId")
                              String userId) {
        System.out.println("userId==="+userId);
        return "아직은 아이디 중복인지 아닌지 모름";
    }
    @PostMapping("/id-check")
    @ResponseBody
    public String idCheckPost(
            @RequestParam(name="userId") String userId,
            @RequestParam(name="userPw") String userPw
            ) {
        System.out.println("userId==="+userId);
        System.out.println("userPw==="+userPw);
        return "아직은 아이디 중복인지 아닌지 모름";
    }
    @PostMapping("/id-check-json")
    @ResponseBody
    public Map<String,Boolean> idCheckPostJson(@RequestBody MemberDto memberDto) {
        //System.out.println(memberDto.getUserId());
        //System.out.println(memberDto.getUserPw());
        String userId =  memberDto.getUserId();
        boolean isDuplicated = memberService.idCheck(userId);
        Map<String,Boolean> returnMap = new HashMap<>();
        returnMap.put("isDuplicate",isDuplicated);
        return returnMap;
    }

}
