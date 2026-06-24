package com.jjang051.security.member.dao;

import com.jjang051.security.member.dto.MemberDto;
import com.jjang051.security.member.dto.SignupDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {
    int signup(SignupDto signupDto);

    MemberDto findByUserId(String userId);

    int updatePassword(@Param("userEmail") String userEmail,
                       @Param("userPw") String userPw);
}