package com.example.mindmap.core.interceptor;

import com.example.mindmap.core.auth.UserHolder;
import com.example.mindmap.core.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class TokenParseInterceptor implements HandlerInterceptor {

    private final JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String method = request.getMethod();
        if(method.equalsIgnoreCase("OPTIONS")){
            return true;
        }

        // 获取登录 JWT
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token)) {
            // 解析 token 并保存
            UserHolder.setUserId(jwtUtils.parseToken(token));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 清理当前线程保存的用户数据
        UserHolder.clear();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}