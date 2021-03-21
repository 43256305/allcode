package com.ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springcloud
 * @description:
 * @author: xjh
 * @create: 2020-11-20 08:23
 **/
@Configuration
public class config {

    @Bean
    public IRule getRibbon(){
        return new ExampleRibbon();
    }

}
