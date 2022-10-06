package com.example.mindmap.service.impl;


import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dto.resp.SearchByTextRespDto;
import com.example.mindmap.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

@Slf4j
public class BookServiceImpl implements BookService {
    @Override
    public RestResp<List<SearchByTextRespDto>> searchBook(String keyword) {
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
        return RestResp.ok(result);
    }
}
