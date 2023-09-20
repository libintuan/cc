package com.sunset.grass.util;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitUtil {
    private static AtomicInteger counts=new AtomicInteger();
    private static Integer qps=2;
    private static long window_Limit=1000L;
    private static long start_time=System.currentTimeMillis();

    public synchronized static boolean tryQuery(){
        if (System.currentTimeMillis()-start_time>window_Limit){
            counts.set(0);
            start_time=System.currentTimeMillis();
        }
        return counts.incrementAndGet()<qps;

    }

    public static void main1(String[] args) {
        for (int i=0;i<200;i++){
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tryQuery()){
                System.out.println("哇哈哈哈哈");
            }else {
                System.out.println("限流了");
            }
        }

    }

    public static void main2(String[] args) {
        // qps 2
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 10; i++) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
            System.out.println(time + ":" + rateLimiter.tryAcquire());
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        int aa=4;
        int bb=5;
        Integer c=aa/bb;
        System.out.println(c);
    }
}
