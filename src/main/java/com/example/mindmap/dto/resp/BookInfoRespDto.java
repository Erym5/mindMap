package com.example.mindmap.dto.resp;

import com.example.mindmap.dao.entity.Root;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Data
public class BookInfoRespDto implements Serializable {
    /**
     * km格式脑图
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "km格式")
    private Root root;
}
