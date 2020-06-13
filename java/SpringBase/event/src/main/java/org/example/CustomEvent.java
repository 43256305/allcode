package org.example;

import org.springframework.context.ApplicationEvent;

/**
 * @program: event
 * @description:
 * @author: xjh
 * @create: 2020-03-19 14:46
 **/
public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }

    public String toString(){
        return "My Custom Event";
    }
}
