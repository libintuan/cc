package com.sunset.grass.aspect;


import com.google.gson.Gson;
import com.sunset.grass.anno.SysLog;
import com.sunset.grass.entity.SysLogBO;
import com.sunset.grass.service.impl.SysLogService;
import com.sunset.grass.util.LoggerHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Aspect
@Order(2)
@Component
public class LoggerAspect {
    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.sunset.grass.anno.SysLog)")
    public void logPointcut(){}

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long beginTime=System.currentTimeMillis();
        System.out.println("bbbbbbbbbbbbbbbbbbbbb");
        Object result=proceedingJoinPoint.proceed();
        long costTime=System.currentTimeMillis()-beginTime;
        try{
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$6666666");
            this.saveLog(costTime,proceedingJoinPoint);
        }catch (Throwable throwable){

        }
        return result;

    }
    @Before("logPointcut()")
    public void beforeCut(JoinPoint joinPoint){
        System.out.println("开始前");

    }

    @After("logPointcut()")
    public void afterCut(JoinPoint joinPoint){
        System.out.println("结束后");

    }

    private void saveLog(long costTime,ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("##########################");
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogBO sysLogBO = new SysLogBO();
        sysLogBO.setExeuTime(costTime);
        sysLogBO.setCreateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        SysLog sysLog = method.getAnnotation(SysLog.class);
        System.out.println("##########################22222");
        if(sysLog != null){
            //注解上的描述
            System.out.println("##########################11111");
            sysLogBO.setRemark(sysLog.value());
        }
        //请求的 类名、方法名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogBO.setClassName(className);
        sysLogBO.setMethodName(methodName);
        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        try{
            List<String> list = new ArrayList<String>();
            for (Object o : args) {
                list.add(new Gson().toJson(o));
            }
            System.out.println("##########################2222222==="+LoggerHelper.outputObj(list));
            sysLogBO.setParams(list.toString());
        }catch (Exception e){ }
        sysLogService.saveLog(sysLogBO);
    }

}
