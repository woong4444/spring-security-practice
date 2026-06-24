package com.jjang051.security.member.service;

import com.jjang051.security.member.dao.MemberDao;
import com.jjang051.security.member.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//Business logic
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;
    public int signup(SignupDto signupDto) {
        SignupDto encodedSignupDto = SignupDto.builder()
                .userId(signupDto.getUserId())
                .userName(signupDto.getUserName())
                .userEmail(signupDto.getUserEmail())
                .userPw(passwordEncoder.encode(signupDto.getUserPw()))
                .build();
        return memberDao.signup(encodedSignupDto);
    }
    public int updatePassword(String userEmail, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        return memberDao.updatePassword(userEmail, encodedPassword);
    }
}