package cn.lxj.bigdate.day05._09_futures;

import java.util.concurrent.*;

/**
 * FutureExample
 * description 有时候使用Future感觉很丑陋，因为你需要间隔检查Future是否已完成，而使用回调会直接收到返回通知。
 * create by lxj 2018/5/16
 **/
public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        // 创建线程
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                // do something
                System.out.println("第一个runable线程......");
            }
        };
        // 创建线程，带回调功能
        Callable<Integer> task2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // do something
                System.out.println("第二个Callable线程......");
                return new Integer(100);
            }
        };
        // 提交第一个线程
        Future<?> f1 = executor.submit(task1);
        // 提交第二个线程
        Future<Integer> f2 = executor.submit(task2);
        // 打印第一个线程是否结束
        System.out.println("第一个线程是否结束？" + f1.isDone());
        // 打印第二个线程是否结束
        System.out.println("第二个线程是否结束？" + f2.isDone());
        // 等待第一个线程结束
        while (f1.isDone()) {
            System.out.println("第一个线程结束");
            break;
        }
        // 等待第二个线程结束
        while (f2.isDone()) {
            System.out.println("第二个线程结束，返回结果：" + f2.get());
            break;
        }
    }
}