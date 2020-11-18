package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: springcloud
 * @description:
 * @author: xjh
 * @create: 2020-11-17 09:27
 **/
@SpringBootApplication
@EnableEurekaServer
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
