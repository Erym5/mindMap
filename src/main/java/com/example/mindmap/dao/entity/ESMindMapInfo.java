package com.example.mindmap.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "book",createIndex = true)
public class ESMindMapInfo {
    @Id
    @Field(type = FieldType.Text)
    /**
     * mapId: 导图ID
     */
    private String mapId;

    @Field(analyzer="ik_max_word")
    private String title;

    @Field(analyzer="ik_max_word")
    public String content;
}
