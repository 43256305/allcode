package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: annotation
 * @description:
 * @author: xjh
 * @create: 2020-03-19 13:49
 **/
//以前的所有bean都需要在applicationContext中写，而用configuration后，可以不用在xml文件中写一个字
@Configuration
@Import(AnthorConfig.class)    //当有多个config类时，需要引入别的依赖，就可以用import引入别的config类
public class HelloWorldConfig {
    //配置初始化方法
    @Bean(initMethod = "init",destroyMethod = "destory")
    public HelloWorld getHelloWorld(){
        //构造器注入依赖
//        HelloWorld helloWorld=new HelloWorld(new HelloLittleWorld());
        HelloWorld helloWorld=new HelloWorld(getHelloLittleWorld());
        //setter注入字符串
        helloWorld.setMessage("hello world!");

        //用import引入AnotherConfig中的bean
        new AnotherHello();
        return helloWorld;
    }

    @Bean
    public HelloLittleWorld getHelloLittleWorld(){
        return new HelloLittleWorld();
    }
}
