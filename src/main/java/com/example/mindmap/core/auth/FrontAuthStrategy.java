package com.example.mindmap.core.auth;

import com.example.mindmap.core.common.exception.BusinessException;
import com.example.mindmap.core.util.JwtUtils;
import com.example.mindmap.manager.UserInfoCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class FrontAuthStrategy implements AuthStrategy {

    private final JwtUtils jwtUtils;

    private final UserInfoCacheManager userInfoCacheManager;

    @Override
    public void auth(String token, String requestUri) throws BusinessException {
        // 统一账号认证
        authSSO(jwtUtils, userInfoCacheManager, token);
    }

}