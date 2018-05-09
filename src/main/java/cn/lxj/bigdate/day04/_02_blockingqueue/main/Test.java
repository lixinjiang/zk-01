package cn.lxj.bigdate.day04._02_blockingqueue.main;

import cn.lxj.bigdate.day04._02_blockingqueue.consumer.Consumer;
import cn.lxj.bigdate.day04._02_blockingqueue.producer.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Test
 * description 测试队列
 * create by lxj 2018/5/7
 **/
public class Test {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingDeque<String>(2);   // 设置队列容量为两个
        // BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        Consumer consumer = new Consumer(queue);    // 实现的Runnable接口
        Producer producer = new Producer(queue);    // 实现的Runnable接口
        for (int i = 0; i < 3; i++) {   // 生产者
            new Thread(producer,"Producer " + (i + 1)).start();
        }
        for (int i = 0; i < 5; i++) {   // 消费者
            new Thread(consumer,"Consumer " + (i + 1)).start();
        }

        new Thread(producer,"Producer " + (5)).start();
    }
}