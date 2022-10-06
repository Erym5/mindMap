package com.example.mindmap.dao.entity;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class Data implements Serializable
{

    private static final long serialVersionUID = 1L;
    private String text;

    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
}
