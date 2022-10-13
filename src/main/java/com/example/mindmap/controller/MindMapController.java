package com.example.mindmap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dao.entity.MindMapInfo;
import com.example.mindmap.dao.entity.rabbitList;
import com.example.mindmap.dto.resp.BookInfoRespDto;
import com.example.mindmap.service.MindMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.mindmap.core.common.constant.ApiRouterConsts;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.MIND_MAP_URL_PREFIX)

@Api(tags = "脑图管理相关接口")
public class MindMapController {

    @Autowired
    MindMapService mindMapService;

    @GetMapping("/{id}")
    @ApiOperation("搜索导图的接口")
    public RestResp<BookInfoRespDto> mindMapCreate(@PathVariable @Param("id") Integer id) {
        return mindMapService.createMindMap(Integer.valueOf(id));
    }

    @PostMapping("/save")
    @ApiOperation("保存导图的接口")
    public RestResp<Void> mindMapSave(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return mindMapService.keepMap(map);
//        System.out.println(map.map_id);
    }

    @PostMapping("/update")
    @ApiOperation("更新导图的接口")
    public RestResp<Void> updateMap(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return mindMapService.newMapById(map);
    }

    @GetMapping("/get/{map_id}")
    @ApiOperation("查询导图的接口")
    public RestResp<MindMapInfo> getMap(@PathVariable @Param("map_id") String map_id) {
        return mindMapService.queryMapById(map_id);
    }

    @GetMapping("/getAll")
    @ApiOperation("查询导图信息的接口")
    public RestResp<List<MindMapInfo>> getMaps() {
        return mindMapService.getMaps();
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除导图的接口")
    public RestResp<Void> deleteMapById(String mapId) {
//        System.out.println(Id);
        return mindMapService.deleteMapById(mapId);
    }

    @PostMapping("/deletebychoices")
    @ApiOperation("删除选定导图的接口")
    public RestResp<Void> deleteMapByChioces(@RequestBody String idsOfMap) {
        List<String> mapIds = JSONObject.parseArray(idsOfMap, String.class);
        System.out.println(mapIds);
//        return RestResp.ok();
        return mindMapService.deleteMapByChioces(mapIds);
    }
}
