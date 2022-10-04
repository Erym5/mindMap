package com.example.mindmap.dao.entity;

import lombok.Data;

@Data
public class MindMapInfo {
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
