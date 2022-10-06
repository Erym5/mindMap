package com.example.mindmap.service.impl;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Override
    public RestResp<String> uploadImg(MultipartFile file) {
        File f = new File("F:/upload");
        if (!f.exists() || !f.isDirectory()){
            f.mkdir();
        }

        int doPos = file.getOriginalFilename().lastIndexOf("."); //获取最后一个点的索引值
        String fileExt= file.getOriginalFilename().substring(doPos + 1).toLowerCase();//获取扩
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + "." + fileExt;
        try {
            Files.copy(file.getInputStream(),new File("F:/upload/" + fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return RestResp.ok(fileName);
    }

    @Override
    public RestResp<Void> getImg(String fileName, HttpServletResponse response) {
        try {
            Files.copy(Paths.get("F:\\upload\\" + fileName),response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return RestResp.ok();
    }
}
