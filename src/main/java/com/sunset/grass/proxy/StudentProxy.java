package com.sunset.grass.proxy;

public class StudentProxy implements Person {
    private Student student;

    public  StudentProxy(Person person){
        this.student= (Student) person;
    }
    @Override
    public void eat() {
        System.out.println("想吃吗");
        student.eat();

    }
}
