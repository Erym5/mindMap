package com.example.mindmap.core;

import com.example.mindmap.core.common.constant.AmqpConsts;
import com.example.mindmap.dao.entity.rabbitList;
import com.example.mindmap.service.MindMapService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class listener {
    private final MindMapService mindMapService;

    @RabbitListener(queues = AmqpConsts.MapChangeMq.QUEUE_DELETE)
    @SneakyThrows
    public void deleteMapByChioces(List<String> mapIds) {
//        List<String> mapIds = maps.getMapIds();
        for(String mapId : mapIds) {
            mindMapService.deleteMapById(mapId);
        }
        log.info("Delete: " + mapIds);
    }
}
