package org.example;

/**
 * @program: annotation
 * @description:
 * @author: xjh
 * @create: 2020-03-19 13:49
 **/
public class HelloWorld {
    private String message;
    private HelloLittleWorld helloLittleWorld;

    public HelloWorld(HelloLittleWorld helloLittleWorld){
        this.helloLittleWorld=helloLittleWorld;
    }

    public void descLittleWorld(){
        helloLittleWorld.desc();
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void init(){
        System.out.println("bean is going through init");
    }

    public void destory(){
        System.out.println("bean is going through destory");
    }
}
