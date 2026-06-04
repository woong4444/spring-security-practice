package com.jjang051.member.service;

import com.jjang051.member.dto.LoginDto;
import com.jjang051.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JdbcTemplate jdbcTemplate;
    public void signup(MemberDto memberDto) {
        String sql = """
                INSERT INTO MEMBER VALUES
                (member_seq.nextval,?,?,
                ?,?,?,?,?,?,sysdate)
                """;
        jdbcTemplate.update(sql,
                memberDto.getUserId(),
                memberDto.getUserName(),
                memberDto.getUserPw(),
                memberDto.getEmail(),
                memberDto.getPhone(),
                memberDto.getAddress(),
                memberDto.getZipcode(),
                memberDto.getDetailAddress()
        );
    }
    public boolean existsUserId(String userId){
        String sql =  "SELECT count(*) FROM MEMBER WHERE USER_ID= ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class, userId);
        return count!=null && count > 0;
    }
    public boolean existsEmail(String email){
        String sql =  "SELECT count(*) FROM MEMBER WHERE EMAIL = ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class, email);
        return count!=null && count > 0;
    }

    public boolean loginCheck(LoginDto loginDto){
        String sql = """
                        SELECT count(*) FROM MEMBER 
                            WHERE user_id=? AND user_pw=?
                     """;
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,
                loginDto.getUserId(),loginDto.getUserPw());
        return count!=null && count > 0;
    }
    public MemberDto loginCheckDto(LoginDto loginDto){
        String sql = """
                        SELECT user_id,user_name FROM MEMBER 
                            WHERE user_id=? AND user_pw=?
                     """;
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rownum) ->
                            MemberDto.builder()
                                    .userId(rs.getNString("user_id"))
                                    .userName(rs.getString("user_name"))
                                    .build(),
                    loginDto.getUserId(), loginDto.getUserPw());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean idCheck(String userId) {
        String sql = """
                        SELECT count(*) FROM MEMBER 
                            WHERE user_id = ?
                     """;
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,userId);
        return count!=null && count > 0;
    }

    public MemberDto getMemberInfo(String userId) {
        String sql = """
                    SELECT user_id,user_name,zipcode FROM MEMBER WHERE user_id=?
                """;
        //RowMapper
        return jdbcTemplate.queryForObject(sql,(rs,rownum)->
                        MemberDto.builder()
                                .userId(rs.getNString("user_id"))
                                .userName(rs.getString("user_name"))
                                .zipcode(rs.getInt("zipcode"))
                        .build()
                ,userId);
    }
}
