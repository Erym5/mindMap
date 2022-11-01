package com.example.mindmap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.example.mindmap.dao.mapper")
//@SuppressWarnings("unchecked")
public class MindMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindMapApplication.class, args);
    }

}
