package cn.lxj._01_mythread.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MyInterruptibly
 * description 观察现象：如果thread-0得到了锁，阻塞。。。thread-1尝试获取锁，如果拿不到，则可以被中断等待
 * create by lxj 2018/5/7
 **/
public class MyInterruptibly {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        MyInterruptibly myInterruptibly = new MyInterruptibly();
        MyThread thread0 = new MyThread(myInterruptibly);
        MyThread thread1 = new MyThread(myInterruptibly);
        thread0.start(); // 会进入死循环，导致thread1获取不到锁
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();    // thread自己将自己打断
        System.out.println("=======================");
    }

    /**
     * 插入操作
     *
     * @param thread
     * @throws InterruptedException
     */
    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();//获取锁的打断会抛出异常，注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + " 得到了锁");
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE) { //死循环
                    break;
                    //插入数据
                }
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + " 执行finally");
            lock.unlock(); // 释放锁
            System.out.println(thread.getName() + " 释放了锁");

        }
    }
}

class MyThread extends Thread {
    private MyInterruptibly test = null;

    public MyThread(MyInterruptibly test) {
        this.test = test;
    }

    public void run() {
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 被中断");
        }
    }
}