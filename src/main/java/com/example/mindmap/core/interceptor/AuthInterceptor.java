package com.example.mindmap.core.interceptor;

import com.example.mindmap.core.auth.FrontAuthStrategy;
import com.example.mindmap.core.auth.UserHolder;
import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.core.common.constant.ApiRouterConsts;
import com.example.mindmap.core.common.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final FrontAuthStrategy frontAuthStrategy;

    private final ObjectMapper objectMapper;

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Authorization,content-type,x-requested-with,Access-Control-Allow-Origin");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization,content-type");
        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            return true;
        }
        // 获取登录 JWT
        String token = request.getHeader("Authorization");
        System.out.println("token::" + token);
        System.out.println(request.getHeader("Origin"));


        // 获取请求的 URI
        String requestUri = request.getRequestURI();

        // 开始认证
        try {
            frontAuthStrategy.auth(token, requestUri);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } catch (BusinessException exception) {
            // 认证失败
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    objectMapper.writeValueAsString(RestResp.fail(exception.getErrorCodeEnum())));
            return false;
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 清理当前线程保存的用户数据
        UserHolder.clear();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}