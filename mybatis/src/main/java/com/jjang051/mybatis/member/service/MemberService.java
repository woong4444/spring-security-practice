package com.jjang051.mybatis.member.service;

import com.jjang051.mybatis.member.dao.MemberDao;
import com.jjang051.mybatis.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    public int signup(MemberDto memberDto){
        return memberDao.signup(memberDto);
    }
}
