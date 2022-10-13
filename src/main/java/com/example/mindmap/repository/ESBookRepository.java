package com.example.mindmap.repository;

import com.example.mindmap.dao.entity.ESMindMapInfo;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 搜索脑图ES操作类
 */
@Component
public interface ESBookRepository extends ElasticsearchRepository<ESMindMapInfo, String> {
    List<ESMindMapInfo> getAllByMapId();

    List<ESMindMapInfo> findAllByContentOrTitle(String title, String content);

    @Highlight(fields = {
            @HighlightField(name = "title"),
            @HighlightField(name = "content")
    })
    @Query("{\"match\":{\"title\":\"?0\"}}")
    List<ESMindMapInfo> find(String keyword);


}
