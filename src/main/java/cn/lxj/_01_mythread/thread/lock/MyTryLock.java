package cn.lxj._01_mythread.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MyTryLock
 * description 观察现象：一个线程获得锁后，另一个线程取不到锁，不会一直等待，不同于Lock，阻塞地去获取锁
 * create by lxj 2018/5/7
 **/
public class MyTryLock {
    private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
    static Lock lock = new ReentrantLock();//notice

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始---START---:");
        new Thread() {
            public void run() {
                // 获取当前线程
                Thread thread = Thread.currentThread();
                boolean tryLock = lock.tryLock();
                System.out.println(thread.getName() + " " + tryLock);
                try {
                    if (tryLock) {// 获取到了锁
                        System.out.println(thread.getName() + " 获取到了锁");
                        for (int i = 0; i < 5; i++) {
                            arrayList.add(i);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(thread.getName() + " 释放了锁");
                    lock.unlock();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                Thread thread = Thread.currentThread();
                boolean tryLock = lock.tryLock();
                System.out.println(thread.getName() + " " + tryLock);
                if (tryLock) {
                    try {
                        System.out.println(thread.getName() + " 得到了锁");
                        for (int i = 0; i < 5; i++) {
                            arrayList.add(i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(thread.getName() + " 得到了锁");
                        lock.unlock();
                    }
                }
            }
        }.start();

        Thread.sleep(1000);
        System.out.println("主线程退出---END---！");
    }
}
