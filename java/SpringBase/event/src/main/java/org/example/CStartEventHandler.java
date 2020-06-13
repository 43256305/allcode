package org.example;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @program: event
 * @description:
 * @author: xjh
 * @create: 2020-03-19 14:34
 **/
//当上下文启动时，ContextStartedEvent 发布，当上下文停止时，ContextStoppedEvent 发布
//如果一个 bean 实现 ApplicationListener，那么每次 ApplicationEvent 被发布（publish）到 ApplicationContext 上，那个 bean 会被通知
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("ContextStartedEvent Received");
    }
}
