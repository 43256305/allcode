package org.example;

/**
 * @program: aboutbean
 * @description:
 * @author: xjh
 * @create: 2020-03-17 21:45
 **/
public class InitDestroy {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        System.out.println("Your Message : " + message);
    }

    public void init(){
        System.out.println("Bean is going through init");
    }

    public void destroy(){
        System.out.println("Bean will destroy now");
    }
}
