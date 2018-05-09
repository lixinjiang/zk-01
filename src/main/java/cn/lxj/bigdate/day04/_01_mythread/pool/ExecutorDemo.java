package cn.lxj.bigdate.day04._01_mythread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * ExecutorDemo
 * description 列出并发包中的各种线程池
 * create by lxj 2018/5/7
 **/
public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        // 获取CPU核数
        int cpuNums = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNums);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(cpuNums);
        ScheduledExecutorService newSecheduledThreadPool = Executors.newScheduledThreadPool(8);

        ScheduledExecutorService newSingleThreadScheduleExecutor = Executors.newSingleThreadScheduledExecutor();
    }
}
