package com.sunset.grass.proxy;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StudentInvication<T> implements InvocationHandler {
    private T target;

    public StudentInvication(T target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态");
        Object result=method.invoke(target,args);
        return result;
    }
}
