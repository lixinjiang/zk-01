package cn.lxj.bigdate.day04._03_jvm.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 死循环演示，使用jstack，命令
 */
public class TestThread {
    /**
     * 死循环演示
     *
     * @param
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("createBusyThread");
                while (true)
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待
     *
     * @param lock
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("createLockThread");
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread(); // 线程阻塞在whiel(true)，直到线程切换，很耗费性能
        br.readLine();
        Object object = new Object();
        createLockThread(object);   // 处于waitting状态，等待notify
    }
}
