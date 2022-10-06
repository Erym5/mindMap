package com.example.mindmap.service;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dao.entity.MindMapInfo;
import com.example.mindmap.dto.resp.BookInfoRespDto;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

public interface MindMapService {
    RestResp<BookInfoRespDto> createMindMap(Integer id);

    RestResp<Void> keepMap(Map<String, Object> map);

    RestResp<Void> newMapById(Map<String, Object> map);

    RestResp<MindMapInfo> queryMapById(String id);

    RestResp<List<MindMapInfo>> getMaps();

    RestResp<Void> deleteMapById(Integer id);
}
