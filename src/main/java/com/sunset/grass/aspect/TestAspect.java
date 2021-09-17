package com.sunset.grass.aspect;

import com.sunset.grass.web.controller.TestController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class TestAspect implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public void getBean(){
        TestController testControllerClass=(TestController)applicationContext.getBean("testController");
    }
}
