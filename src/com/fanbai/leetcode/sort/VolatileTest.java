package com.fanbai.leetcode.sort;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
                System.out.println(Thread.currentThread().getName() + "--" + race);

            });
            threads[i].start();
            threads[i].join();
        }

        TimeUnit.SECONDS.sleep(9L);

//        for (int i = 0; i < 20; i++) {
//            threads[i].join();
//
//        }

        System.out.println(race);


        List<Integer> list = new ArrayList<>();
    }
}
