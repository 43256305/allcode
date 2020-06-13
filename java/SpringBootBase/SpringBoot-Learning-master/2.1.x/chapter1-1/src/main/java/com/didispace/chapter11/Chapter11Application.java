package com.didispace.chapter11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//程序入口
@SpringBootApplication
//@ComponentScan("com.didispace")   指定spring要扫描的包（默认扫描与主程序同级的包，如果把包创建在了主程序上面，就要指定）
public class Chapter11Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter11Application.class, args);
	}

}
