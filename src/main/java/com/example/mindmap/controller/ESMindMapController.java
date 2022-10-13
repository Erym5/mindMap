package com.example.mindmap.controller;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.core.common.constant.ApiRouterConsts;
import com.example.mindmap.dao.entity.ESMindMapInfo;
import com.example.mindmap.service.ESMindMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "EsMapController")
@Tag(name = "EsMapController",description = "搜索商品管理")
@RequestMapping(ApiRouterConsts.ES_URL_PREFIX)
public class ESMindMapController {
    @Autowired
    private ESMindMapService esMindMapService;

    @ApiOperation(value = "导入所有数据库中脑图到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public RestResp<Void> importAllList() {
        esMindMapService.importAll();
        return RestResp.ok();
    }

    @ApiOperation(value = "获取所有数据库中脑图")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public RestResp<Iterable> getAll() {
        return esMindMapService.getAll();
    }

    @ApiOperation(value = "删除所有脑图")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResp<Void> deleteAll() {
        esMindMapService.deleteAll();
        return RestResp.ok();
    }
    
    @ApiOperation(value = "在title、content查找关键字")
    @RequestMapping(value = "/getByKeyword", method = RequestMethod.GET)
    @ResponseBody
    public RestResp<RestResp<List<ESMindMapInfo>>> searchByKeyword(String keyword) {
        return RestResp.ok(esMindMapService.searchByKeyword(keyword));
    }
    
    

}
