package com.example.demo.util;

import com.example.demo.factory.NamedThreadFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ThreadPoolExecutor newFixedThreadPool(int nThreads, long keepAliveTime, String name) {
        return new ThreadPoolExecutor(nThreads,
                nThreads,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new NamedThreadFactory(name) {
                });
    }
}
