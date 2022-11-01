package com.example.mindmap.core.config;

import com.example.mindmap.core.common.constant.ApiRouterConsts;
import com.example.mindmap.core.interceptor.AuthInterceptor;
import com.example.mindmap.core.interceptor.TokenParseInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    private final TokenParseInterceptor tokenParseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 权限认证拦截
        registry.addInterceptor(authInterceptor)
                // 拦截会员中心相关请求接口
                .addPathPatterns(ApiRouterConsts.API_URL_PREFIX + "/**")
                // 放行登录注册相关请求接口
                .excludePathPatterns(ApiRouterConsts.USER_URL_PREFIX + "/login")
                .order(0);

        // Token 解析拦截器
        registry.addInterceptor(tokenParseInterceptor)
                // 拦截小说内容查询接口，需要解析 token 以判断该用户是否有权阅读该章节（付费章节是否已购买）
                .addPathPatterns(ApiRouterConsts.API_URL_PREFIX + "/**")
                .excludePathPatterns(ApiRouterConsts.USER_URL_PREFIX + "/login")
                .order(1);
    }
}
