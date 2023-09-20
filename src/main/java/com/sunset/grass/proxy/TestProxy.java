package com.sunset.grass.proxy;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main1(String[] args) {
        Person aaa=new Student("aaa");
        StudentProxy studentProxy=new StudentProxy(aaa);
        studentProxy.eat();
    }

    public static void main2(String[] args) {

        Person aaa=new Student("aaa");
        InvocationHandler studentInvication=new StudentInvication<Person>(aaa);

        Person student = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, studentInvication);
        student.eat();
    }

    public static void main(String[] args) {

    }
}
