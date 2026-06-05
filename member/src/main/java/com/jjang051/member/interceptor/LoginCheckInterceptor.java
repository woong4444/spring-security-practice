package com.jjang051.member.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

//요청을 처리하기 전에 중간에서 낚아채는 역할을 한다.
//브라우져 => Interceptor => Controller => View
//AoP   controller => AoP => Service
public class LoginCheckInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedMemberDto") == null) {
            response.sendRedirect("/member/login");
            return false;
        }
        return true;
    }
}
