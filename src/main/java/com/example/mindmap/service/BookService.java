package com.example.mindmap.service;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dto.resp.SearchByTextRespDto;

import java.util.List;

public interface BookService {
    RestResp<List<SearchByTextRespDto>> searchBook(String keyword);
}
