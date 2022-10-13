package com.example.mindmap.service;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dao.entity.ESMindMapInfo;

import java.util.List;

public interface ESMindMapService {
    /**
     * 从数据库中导入所有商品到ES
     */
    RestResp<Void> importAll();

    RestResp<Iterable> getAll();

    RestResp<Void> deleteAll();

    RestResp<List<ESMindMapInfo>> searchByKeyword(String keyword);


}
