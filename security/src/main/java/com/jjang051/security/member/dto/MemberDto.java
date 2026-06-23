package com.jjang051.security.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
    private String userId;
    private String userName;
    private int no;
    private String userPw;
    private LocalDateTime regDate;
    private String role;
}
