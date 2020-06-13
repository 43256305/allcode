package org.example;

/**
 * @program: hellospring
 * @description:
 * @author: xjh
 * @create: 2020-03-17 20:47
 **/
//spring配置元数据可以用：xml，java注释，java代码
public class HelloWorld {
    private String message;
    public void setMessage(String message){
        this.message = message;
    }
    public void getMessage(){
        System.out.println("Your Message : " + message);
    }
}
