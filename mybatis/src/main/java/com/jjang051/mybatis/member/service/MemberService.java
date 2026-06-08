package com.jjang051.mybatis.member.service;

import com.jjang051.mybatis.member.dao.MemberDao;
import com.jjang051.mybatis.member.dto.LoginDto;
import com.jjang051.mybatis.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    //테스트 코드 작성
    private final MemberDao memberDao;
    public int signup(MemberDto memberDto){
        MemberDto memberDto1 = MemberDto.builder()
                .userId("jjang051")
                .userName("장성호")
                .userPw("1234")
                .phone("01011111111")
                .email("jjang051@hanmail.net")
                .address("장항동")
                .detailAddress("111-111")
                .profile("aaaaaaaaaaaaaa")
                .thumbnailProfile("aaaaaaaaaaaaaaaaaa")
                .zipcode(11111)
                .regDate(LocalDateTime.now())
                .build();
        return memberDao.signup(memberDto1);
    }
    public MemberDto login(LoginDto loginDto){
        return  memberDao.login(loginDto);
    }
    public Integer idCheck(String userId){
        return memberDao.idCheck(userId);
    }

    public Integer deleteMember(LoginDto loginDto){
        return memberDao.deleteMember(loginDto);
    }
}
