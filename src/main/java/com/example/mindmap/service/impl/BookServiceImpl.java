package com.example.mindmap.service.impl;


import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dao.entity.MindMapInfo;
import com.example.mindmap.dto.resp.SearchByTextRespDto;
import com.example.mindmap.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor

@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public RestResp<List<SearchByTextRespDto>> searchBook(String keyword) {
        String key = "keyword_" + keyword;
        ValueOperations<String, List<SearchByTextRespDto>> operations = redisTemplate.opsForValue();

        //判断数据是否在缓存中
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            List<SearchByTextRespDto> result = operations.get(key);
            return RestResp.ok(result);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:3000/search?text=" + keyword;
            ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
            List<Map> returnMap = (List<Map>) exchange.getBody().get("data");
            List<SearchByTextRespDto> result = new ArrayList<>();
            for (Map<String, Object> map : returnMap) {
                SearchByTextRespDto res = new SearchByTextRespDto();
                if (map.get("id") == null) continue;
                else {
                    res.setId((Integer) map.get("id"));
                    res.setPicUrl((String) map.get("cover_url"));
                    res.setAuthorName((String) map.get("abstract"));
                    res.setBookName((String) map.get("title"));
                    result.add(res);
                }
            }
            operations.set(key, result, 5, TimeUnit.HOURS);
            return RestResp.ok(result);
        }
    }
}
