package com.example.mindmap.repository;

import com.example.mindmap.dao.entity.MindMapInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookRepository extends MongoRepository<MindMapInfo, String> {
    @Override
    List<MindMapInfo> findAll();
}
