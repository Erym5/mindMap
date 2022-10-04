package com.example.mindmap.dao.entity;

import java.util.ArrayList;
import java.util.List;
public class Root
{
    private Data data;

    private List<Children> children;

    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setChildren(List<Children> children){
        this.children = children;
    }
    public List<Children> getChildren(){
        return this.children;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"data\":")
                .append(data);
        sb.append(",\"children\":")
                .append(children);
        sb.append('}');
        return sb.toString();
    }
}