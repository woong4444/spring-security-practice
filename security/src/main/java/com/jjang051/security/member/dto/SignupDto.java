package com.jjang051.security.member.dto;

import lombok.*;

import javax.management.relation.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private Role role;
}
