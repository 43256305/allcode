package com.didispace.chapter11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller处理http请求，但是Controller必须配合模板使用（也就是返回的都是视图），如：Thymeleaf
//@RestController是@ResponseBody和@Controller的组合注解（组合返回json），而用@RestController可以直接返回json
@RestController
public class HelloController {

    @Autowired
    Book book;

    @Value("${com.didispace.chapter11.value}")
    private String randomString;

    //@RequestMapping配置url映射，可以用在控制器（controller）上，也可以用在控制器的某个方法上
    //当作用在控制器上时，相当于作用在每个方法上，访问url变为：localhost:8080/（控制器mapping）/（方法mapping）
    //现在一般直接用GetMapping，PostMapping等
    @RequestMapping("/hello")
    public String index() {
        System.out.println(book.getName());
        System.out.println(book.getDesc());
        System.out.println("自定义属性："+randomString);
        return "Hello World";
    }

}

//本系列教程：http://blog.didispace.com/spring-boot-learning-21-1-1/
//通过Spring Boot实现的服务，只需要依靠一个Java类，把它打包成jar，并通过java -jar命令就可以运行起来