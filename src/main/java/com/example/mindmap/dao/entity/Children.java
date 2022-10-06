package com.example.mindmap.dao.entity;

import com.example.mindmap.dao.entity.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Repository
public class Children implements Serializable
{

    private static final long serialVersionUID = 1L;

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
}