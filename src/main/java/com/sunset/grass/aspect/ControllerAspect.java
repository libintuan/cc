package com.sunset.grass.aspect;

import com.sunset.grass.util.LoggerHelper;
import com.sunset.grass.web.form.test.BaseForm;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class ControllerAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("bean(*Controller)")
    public void pointCutController(){}

    @Around("pointCutController()")
    public Object aroundPoint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("22222222");
        Object rsult=proceedingJoinPoint.proceed();
        String name=this.bulidServiceName(proceedingJoinPoint);
        logger.info("{},请求完成，返回结果为vo={}",name,LoggerHelper.outputObj(rsult));
        System.out.println("3333333333333333，返回结果为："+ LoggerHelper.outputObj(rsult));
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
        if ("queryUserInfos".equals(proceedingJoinPoint.getSignature().getName())){
            logger.info("切到了对应方法¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥");
            Object[] args=proceedingJoinPoint.getArgs();
            logger.info("请求URL={},form={}",url+name,LoggerHelper.outputObj(proceedingJoinPoint.getArgs()));
            if (args!=null){
                for (Object o:args) {
                    if (o instanceof BaseForm){
                        BaseForm baseForm=(BaseForm)o;
                        baseForm.setChannel("222");
                    }
                }
            }
        logger.info("请求URL={},form={}",url+name,LoggerHelper.outputObj(proceedingJoinPoint.getArgs()));
        }
        System.out.println(url+"___****"+name);
        return url+name;
    }
}
