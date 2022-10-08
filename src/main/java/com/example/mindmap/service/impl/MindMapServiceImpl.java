package com.example.mindmap.service.impl;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.core.util.Km2MindMapInfo;
import com.example.mindmap.dao.MongoDao;
import com.example.mindmap.dao.entity.MindMapInfo;
import com.example.mindmap.dto.resp.BookInfoRespDto;
import com.example.mindmap.service.MindMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.mindmap.core.util.Book2MindMap.createMap;

@Service
public class MindMapServiceImpl implements MindMapService {
    @Autowired
    private MongoDao mongoDao;
//    @Autowired
//    private RedisTemplate redisTemplate;

    @Override
    public RestResp<BookInfoRespDto> createMindMap(Integer id) {
        return RestResp.ok(createMap(id));
    }
    @Override
    public RestResp<Void> keepMap(Map<String, Object> map) {
        MindMapInfo result = Km2MindMapInfo.km2MindMapinfo(map);
        mongoDao.saveMap(result);
        return RestResp.ok();
    }

    @Override
    public RestResp<Void> newMapById(Map<String, Object> map) {
        MindMapInfo result = Km2MindMapInfo.km2MindMapinfo(map);
        mongoDao.updateMapById(result);
        return RestResp.ok();
    }
    @Override
    public RestResp<MindMapInfo> queryMapById(String id) {
//        String key = "map_" + id;
//        ValueOperations<String, MindMapInfo> operations = redisTemplate.opsForValue();

        //判断数据是否在缓存中
//        boolean hasKey = redisTemplate.hasKey(key);
//        if (hasKey) {
//            MindMapInfo mindMapInfo = operations.get(key);
//            System.out.println("缓存:" );
//            return RestResp.ok(mindMapInfo);
//        }
//        else {
        MindMapInfo mindMapInfo = mongoDao.getMapById(id);
        System.out.println("数据库:" + mindMapInfo);
//        operations.set(key, mindMapInfo, 5, TimeUnit.HOURS);
        return RestResp.ok(mindMapInfo);
//        }
    }

    @Override
    public RestResp<List<MindMapInfo>> getMaps() {
        return RestResp.ok(mongoDao.getMaps());
    }

    @Override
    public RestResp<Void> deleteMapById(String mapId) {
        mongoDao.deleteMapById(mapId);
        return RestResp.ok();
    }
}
