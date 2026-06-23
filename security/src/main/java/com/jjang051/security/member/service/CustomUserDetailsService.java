package com.jjang051.security.member.service;

import com.jjang051.security.member.dao.MemberDao;
import com.jjang051.security.member.dto.CustomUserDetails;
import com.jjang051.security.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        MemberDto loggedMemberDto = memberDao.findByUserId(userId);
                return new CustomUserDetails(loggedMemberDto);
    }
}
