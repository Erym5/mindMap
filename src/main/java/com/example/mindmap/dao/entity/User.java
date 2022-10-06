package com.example.mindmap.dao.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
@Repository
public class User implements Serializable {

    /**
     * 主键
     */
    private int id;

    /**
    用户名
    */
    private String name;

    /**
    密码
    */
    private String pwd;

}
