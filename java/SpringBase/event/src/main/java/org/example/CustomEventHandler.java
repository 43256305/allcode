package org.example;

import org.springframework.context.ApplicationListener;

/**
 * @program: event
 * @description:
 * @author: xjh
 * @create: 2020-03-19 14:49
 **/
public class CustomEventHandler implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        System.out.println(customEvent.toString());
    }
}
