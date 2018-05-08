package cn.lxj._01_mythread.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MyLock
 * description 测试Lock接口
 * create by lxj 2018/5/7
 **/
public class MyLock {
    private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
    static Lock lock = new ReentrantLock(); // 注意这个地方,成员变量

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始---START---:");
        new Thread() {
            public void run() {
                Thread thread = Thread.currentThread();
                lock.lock();    // 锁定成员变量去控制线程安全
                try {
                    System.out.println(thread.getName() + "得到了锁");
                    for (int i = 0; i < 5; i++) {
                        arrayList.add(i);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                Thread thread = Thread.currentThread();
                lock.lock();    //锁定成员变量去控制线程安全
                try {
                    System.out.println(thread.getName() + " 得到了锁");
                    for (int i = 0; i < 5; i++) {
                        arrayList.add(i);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    System.out.println(thread.getName() + " 释放了锁");
                    lock.unlock();
                }

            }
        }.start();
        Thread.sleep(1000);
        System.out.println("主线程退出---END---！");
    }
}
