package com.sunset.grass.service.impl;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

@Component
public class TaskService {

    private static String aa="123";

    @PostConstruct
    public void taskInit(){
        ScheduledExecutorService scheduledThreadPoolExecutor= Executors.newSingleThreadScheduledExecutor();
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(aa);
                System.out.println("eeeeeeeeeeeee");
            }
        },10,10,TimeUnit.SECONDS);

    }

    @PreDestroy
    public void destoryTask(){

        System.out.println("销毁");
    }
}
