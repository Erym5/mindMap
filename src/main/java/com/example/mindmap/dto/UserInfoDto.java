package com.example.mindmap.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Data
@Builder
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

//    private Integer status;

}
