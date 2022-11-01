package com.example.mindmap.core.util;

import com.example.mindmap.dao.entity.MindMapInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Km2MindMapInfo {

    public static MindMapInfo km2MindMapinfo(Map<String,Object> map) {
        MindMapInfo result = new MindMapInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> root = (Map<String, Object>) map.get("root");
        Map<String, Object> data = (Map<String, Object>) root.get("data");
        result.setMapId((String) data.get("id"));
        result.setTitle((String) data.get("text"));
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        result.setContent(jsonStr);
        return result;
    }
}
