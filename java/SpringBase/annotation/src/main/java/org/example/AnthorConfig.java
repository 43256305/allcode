package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: annotation
 * @description:
 * @author: xjh
 * @create: 2020-03-19 14:04
 **/
@Configuration
public class AnthorConfig {
    @Bean
    public AnotherHello getAnotherHello(){
        return new AnotherHello();
    }
}
