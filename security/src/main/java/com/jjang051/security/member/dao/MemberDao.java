package com.jjang051.security.member.dao;

import com.jjang051.security.member.dto.SignupDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int signup(SignupDto signupDto);
}
