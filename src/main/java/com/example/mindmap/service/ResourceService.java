package com.example.mindmap.service;

import com.example.mindmap.core.common.RestResp.RestResp;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ResourceService {
    RestResp<String> uploadImg (MultipartFile file);

    RestResp<Void> getImg (String fileName, HttpServletResponse response);
//
}
