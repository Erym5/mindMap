package com.example.mindmap.cotroller;

import com.example.mindmap.dto.req.SearchByTextReqDto;
import com.example.mindmap.dto.resp.SearchByTextRespDto;
import com.example.mindmap.service.BookService;
import com.example.mindmap.service.impl.BookServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Api(tags = "书籍管理相关接口")
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/{keyword}")
    @ApiOperation("搜索书籍的接口")
//    @ApiImplicitParam(name = "keyword", value = "关键字", required = true)
    public List<SearchByTextRespDto> findBookByText(@PathVariable("keyword") String keyword) {
        return bookService.searchBook(keyword);
    }
}
