package com.jjang051.mybatis.member.dao;

import com.jjang051.mybatis.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    MemberDto findAll();
}
