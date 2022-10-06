package com.example.mindmap.dao.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Data
@Document(collection = "mapInfo")
public class MindMapInfo implements Serializable
{

    private static final long serialVersionUID = 1L;
    public int id;

    public String mapId;

    public String title;

    public String content;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"mapId\":\"")
                .append(mapId).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
