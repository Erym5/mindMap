package com.example.mindmap.manager;

import com.example.mindmap.core.common.constant.CacheConsts;
import com.example.mindmap.dao.entity.User;
import com.example.mindmap.dao.mapper.UserInfoMapper;
import com.example.mindmap.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserInfoCacheManager {

    private final UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.USER_INFO_CACHE_NAME)
    public UserInfoDto getUser(Integer userId) {
        User userInfo = (User) userInfoMapper.selectById(userId);
        if (Objects.isNull(userInfo)) {
            return null;
        }
        return UserInfoDto.builder()
                .id(userInfo.getId()).build();
    }


}