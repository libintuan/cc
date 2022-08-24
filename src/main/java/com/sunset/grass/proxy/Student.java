package com.sunset.grass.proxy;

public class Student implements Person {
    private String name;

    public Student (String name){
        this.name=name;
    }

    @Override
    public void eat() {
        System.out.println(name+"爱吃大西瓜");
    }
}
