package com.example.mindmap.dao.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Data
public class rabbitList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> mapIds;
}
