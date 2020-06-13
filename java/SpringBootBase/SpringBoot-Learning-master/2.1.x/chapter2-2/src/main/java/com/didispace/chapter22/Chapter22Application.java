package com.didispace.chapter22;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//添加@EnableSwagger2Doc使程序可以用Swagger2
//swagger用于管理RESTful，即测试那些get,post,delete方法
// http://localhost:8080/swagger-ui.html
@EnableSwagger2Doc
@SpringBootApplication
public class Chapter22Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter22Application.class, args);
    }

}
