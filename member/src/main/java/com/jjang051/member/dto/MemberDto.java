package com.jjang051.member.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Integer no;

    @NotBlank(message = "아이디는 필수입력 사항입니다.")
    @Size(min=3, max = 20, message = "아이디는 3~20까지입니다.")
    private String userId;      // 회원아이디

    private String userName;    // 이름

    @NotBlank(message = "비밀번호는 필수입력 사항입니다.")
    @Size(min=3, max = 20, message = "비밀번호는 3~20까지입니다.")
    private String userPw;      // 비밀번호

    @NotBlank(message = "비밀번호는 필수입력 사항입니다.")
    @Size(min=3, max = 20, message = "비밀번호는 3~20까지입니다.")
    private String userPwCheck;      // 비밀번호



    @NotBlank(message = "이메일은 필수입력 사항입니다.")
    @Email(message = "이메일 형식에 맞게 입력해 주세요.")
    private String email;       // 이메일

    private String phone;       // 전화번호

    private String address;     // 주소

    private Integer zipcode;

    private String detailAddress;

    private String profile;

    private LocalDateTime regDate; // 가입일
}