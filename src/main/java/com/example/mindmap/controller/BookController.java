package com.example.mindmap.controller;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dto.resp.SearchByTextRespDto;
import com.example.mindmap.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.example.mindmap.core.common.constant.ApiRouterConsts;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.BOOK_URL_PREFIX)

@Api(tags = "书籍管理相关接口")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/search")
    @ApiOperation("搜索书籍的接口")
    public RestResp<List<SearchByTextRespDto>> findBookByText(@Param("搜索关键字") String keyword) {
        return bookService.searchBook(keyword);
    }
//    @GetMapping("/image")
//    @ApiOperation("读取本地图片测试")
//    @ResponseBody
//    public String getImage(HttpServletResponse response) {
//        try {
//            Files.copy(Paths.get("C:\\Users\\65173\\Desktop\\test.jpg"),response.getOutputStream());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return url + fileName
//    }
}
