package com.jjang051.mybatis.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Integer no;
    private String userId;
    private String userName;
    private String userPw;
    private String userPwCheck;
    private String email;
    private String phone;
    private int zipcode;
    private String address;
    private String detailAddress;
    private String thumbnailProfile;
    private String profile;
    private LocalDateTime regDate;
}
