package org.example;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @program: event
 * @description:
 * @author: xjh
 * @create: 2020-03-19 14:35
 **/
//ApplicationListener接口只有一个方法
public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("ContextStartedEvent Received");
    }
}
