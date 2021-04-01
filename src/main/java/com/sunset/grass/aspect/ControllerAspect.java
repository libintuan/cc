package com.sunset.grass.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class ControllerAspect {


    @Pointcut("bean(*Controller)")
    public void pointCutController(){}

    @Around("pointCutController()")
    public Object aroundPoint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("22222222");
        Object rsult=proceedingJoinPoint.proceed();
        String name=this.bulidServiceName(proceedingJoinPoint);
        System.out.println("3333333333333333");
        return rsult;

    }

    private String bulidServiceName(ProceedingJoinPoint proceedingJoinPoint){
        Signature signature= proceedingJoinPoint.getSignature();
        MethodSignature methodSignature=(MethodSignature)signature;
        Method method=methodSignature.getMethod();
        RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
        String url="";
        if (requestMapping!=null&&requestMapping.value()!=null&&requestMapping.value().length!=0){
        url=requestMapping.value()[0];

        }
        String name=method.getDeclaringClass().getName();
        System.out.println(url+"___****"+name);
        return url+name;
    }
}
