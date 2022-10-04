package com.example.mindmap.dto.resp;

import com.example.mindmap.dao.entity.Root;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BookInfoRespDto {
    /**
     * km格式脑图
     */
    @Schema(description = "km格式")
    private Root root;
}
