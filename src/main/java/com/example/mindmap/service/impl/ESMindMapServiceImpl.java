package com.example.mindmap.service.impl;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dao.MongoDao;
import com.example.mindmap.dao.entity.ESMindMapInfo;
import com.example.mindmap.dao.entity.MindMapInfo;
import com.example.mindmap.repository.BookRepository;
import com.example.mindmap.repository.ESBookRepository;
import com.example.mindmap.service.ESMindMapService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ESMindMapServiceImpl implements ESMindMapService {

    private final ESBookRepository esBookRepository;
    private final BookRepository bookRepository;
    private final MongoDao mongoDao;

//    private final TransactionTemplate transactionTemplate;

    public ESMindMapServiceImpl(ESBookRepository esBookRepository, BookRepository bookRepository, MongoDao mongoDao) {
//        this.transactionTemplate = transactionTemplate;
        this.esBookRepository = esBookRepository;
        this.bookRepository = bookRepository;
        this.mongoDao = mongoDao;
    }

    @Override
    public RestResp<Void> importAll() {
        List<MindMapInfo> mindMapInfo = mongoDao.getMaps();
        List<ESMindMapInfo> esMindMapInfo = new ArrayList<>();
        for (MindMapInfo mindMapInfo1 : mindMapInfo){
            esMindMapInfo.add(mindMapInfo1.toESMindMapInfo());
        }
        assert esMindMapInfo != null;
        BeanUtils.copyProperties(mindMapInfo, esMindMapInfo);

//        System.out.println(esMindMapInfo);
//        System.out.println(mindMapInfo);
//        System.out.println();

        esBookRepository.saveAll(esMindMapInfo);
        return RestResp.ok();
    }

    @Override
    public RestResp<Void> deleteAll() {
        Iterable<ESMindMapInfo> esMindMapInfos = esBookRepository.findAll();
        esBookRepository.deleteAll(esMindMapInfos);
        return RestResp.ok();
    }

    @Override
    public RestResp<Iterable> getAll() {
        Iterable<ESMindMapInfo> items = esBookRepository.findAll();
        items.forEach(item -> System.out.println( item ));
        return RestResp.ok(items);
    }

    @Override
    public RestResp<List<ESMindMapInfo>> searchByKeyword(String keyword){
        return RestResp.ok(esBookRepository.findAllByContentOrTitle(keyword, keyword));
    }
}
