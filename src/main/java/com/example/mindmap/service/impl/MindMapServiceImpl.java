package com.example.mindmap.service.impl;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.core.util.Km2MindMapInfo;
import com.example.mindmap.dao.MongoDao;
import com.example.mindmap.dao.entity.MindMapInfo;
import com.example.mindmap.dao.entity.rabbitList;
import com.example.mindmap.dto.resp.BookInfoRespDto;
import com.example.mindmap.service.MindMapService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.mindmap.core.common.constant.AmqpConsts.MapChangeMq.EXCHANGE_NAME;
import static com.example.mindmap.core.common.constant.AmqpConsts.MapChangeMq.QUEUE_DELETE;
import static com.example.mindmap.core.util.Book2MindMap.createMap;

@Service
public class MindMapServiceImpl implements MindMapService {
    @Autowired
    private MongoDao mongoDao;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public RestResp<BookInfoRespDto> createMindMap(Integer id) {
        return RestResp.ok(createMap(id));
    }

    /**
     *
     * @param map 导图信息
     * @return  成功信息
     */
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
        MindMapInfo mindMapInfo = mongoDao.getMapById(id);
        return RestResp.ok(mindMapInfo);
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

    @Override
    public RestResp<Void> deleteMapByChioces(List<String> mapIds) {
        amqpTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_DELETE, mapIds);
        return RestResp.ok();
    }
}
