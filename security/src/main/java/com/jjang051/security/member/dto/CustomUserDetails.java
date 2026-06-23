package com.jjang051.security.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {
    private final MemberDto memberDto;

    public CustomUserDetails(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(memberDto.getRole())
        );
    }

    @Override
    public @Nullable String getPassword() {
        return memberDto.getUserPw();
    }

    @Override
    public String getUsername() {
        return memberDto.getUserId();
    }
}
