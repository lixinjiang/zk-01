package cn.lxj.bigdate.day04._02_blockingqueue.producer;

import java.util.concurrent.BlockingQueue;

/**
 * Producer
 * description 生产者
 * create by lxj 2018/5/7
 **/
public class Producer implements Runnable {
    // 线程安全的队列
    BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("I have made a product: " + Thread.currentThread().getName());
            String temp = "A Product, 生产线程： " + Thread.currentThread().getName();
            queue.put(temp); // 如果队列是满了的话，会阻塞当前的线程
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}