package com.example.mindmap.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SearchByTextRespDto {
    /**
     * ID
     */
    @Schema(description = "小说ID")
    private Integer id;

    /**
     * 书籍封面地址
     */
    @Schema(description = "书籍封面地址")
    private String picUrl;

    /**
     * 书籍名
     */
    @Schema(description = "书籍名")
    private String bookName;

    /**
     * 作家名
     */
    @Schema(description = "作家名")
    private String authorName;
}
