package cn.lxj._02_blockingqueue.consumer;

import java.util.concurrent.BlockingQueue;

/**
 * Consumer
 * description 消费者
 * create by lxj 2018/5/7
 **/
public class Consumer implements Runnable {
    // 线程安全的队列
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String consumer = Thread.currentThread().getName();
            // System.out.println(consumer);
            String temp = queue.take(); // 如果队列为空，会阻塞当前线程
            System.out.println(consumer + " get a product：" + temp); // 从队列中拿出一个
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
