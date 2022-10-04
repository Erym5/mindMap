package com.example.mindmap.cotroller;

import com.example.mindmap.dto.resp.BookInfoRespDto;
import com.example.mindmap.dto.resp.SearchByTextRespDto;
import com.example.mindmap.service.BookService;
import com.example.mindmap.service.MindMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/mindMap")
@Api(tags = "导图管理相关接口")
public class MindMapController {
    @Autowired
    MindMapService mindMapService;
    @GetMapping("/{id}")
    @ApiOperation("搜索导图的接口")
//    @ApiImplicitParam(name = "id", value = "序号", required = true)
    public BookInfoRespDto mindMapCreate(@PathVariable("id") Integer id) {
        return mindMapService.createMindMap(Integer.valueOf(id));
    }
}
