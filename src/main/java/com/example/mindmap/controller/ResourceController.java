package com.example.mindmap.controller;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.core.common.constant.ApiRouterConsts;
import com.example.mindmap.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.RESOURCE_URL_PREFIX)
@Api(tags = "资源管理相关接口")
public class ResourceController {
    @Autowired
    ResourceService resourceService;
    @PostMapping("/upload")
    @ApiOperation("上传图片的的接口")
    @ResponseBody
    public RestResp<String> uploadImg(MultipartFile file) {
        return resourceService.uploadImg(file);
    }

    @GetMapping()
    @ApiOperation("获取图片的的接口")
    public RestResp<Void> getImg(@RequestParam(name = "fileName") String fileName, HttpServletResponse response) {
        return resourceService.getImg(fileName, response);
    }
}
