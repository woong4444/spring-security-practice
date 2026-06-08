package com.jjang051.mybatis.member.dao;

import com.jjang051.mybatis.member.dto.LoginDto;
import com.jjang051.mybatis.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    MemberDto findAll();

    int signup(MemberDto dto);

    MemberDto login(LoginDto loginDto);

    Integer idCheck(String userId);

    Integer deleteMember(LoginDto loginDto);

}
