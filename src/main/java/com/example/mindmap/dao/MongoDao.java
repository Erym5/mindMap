package com.example.mindmap.dao;

import com.example.mindmap.dao.entity.MindMapInfo;
import com.mongodb.client.result.UpdateResult;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Component
public class MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /*保存导图*/
    public void saveMap(MindMapInfo map) {
        mongoTemplate.insert(map, "mapInfo");
    }

    /*更新导图*/
    public void updateMapById(MindMapInfo map) {
        Query query = new Query(Criteria.where("mapId").is(map.getMapId()));
        Update update = new Update().set("title", map.getTitle()).set("content", map.getContent());
        mongoTemplate.updateFirst(query,update,MindMapInfo.class);
    }

    /*根据mapId查询导图*/
    public MindMapInfo getMapById(String id){
        Query query = new Query(Criteria.where("mapId").is(id));
        return mongoTemplate.findOne(query, MindMapInfo.class);
    }

    /*获取导图*/
    public List<MindMapInfo> getMaps() {
        return mongoTemplate.findAll(MindMapInfo.class);
    }

    /*根据id删除导图*/
    public void deleteMapById(String mapId) {
        Query query = new Query(Criteria.where("mapId").is(mapId));
        mongoTemplate.remove(query, MindMapInfo.class);
    }
}
