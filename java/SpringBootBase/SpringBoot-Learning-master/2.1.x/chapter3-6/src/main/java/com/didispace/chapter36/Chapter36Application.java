package com.didispace.chapter36;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//上一篇使用的是注解，这一片使用xml文件
//在应用主类中增加mapper的扫描包配置，这里配置的是扫描UserMapper接口（因为这个接口放置在包中，所以要重新扫描）
@MapperScan("com.didispace.chapter36.mapper")
@SpringBootApplication
public class Chapter36Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter36Application.class, args);
	}

}
