package cn.lxj.bigdate.zk01;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * ZkDistributedQueue
 * description 分布式队列
 * create by lxj 2018/5/3
 **/
public class ZkDistributedQueue {
    // 邮箱上限为10封
    private static final int MAILBOX_MAX_SIZE = 10;

    // 邮箱路径
    private static final String MAILBOX_ROOT_PATH = "/mailBox";

    // 信件节点
    private static final String LETTER_NODE_NAME = "letter_";

    // 生产者线程负责接收信件(往邮箱内加入邮件)
    static class Producer extends Thread {
        // 每个生产者都持有一个zkClient长连接客户端（与zk保持通讯）
        ZooKeeper zkClient;

        @Override
        public void run() {
            while (true) {
                try {
                    if (getLetterNum() == MAILBOX_MAX_SIZE) { // 邮箱已满
                        System.out.println("mailBox has been full");
                        // 创建Watcher，监控子节点的变化
                        Watcher watcher = new Watcher() {
                            public void process(WatchedEvent watchedEvent) {
                                // 生产者已停止，只有消费者在活动，所以只可能出现发送信件的动作
                                System.out.println("mailBox has been not full");
                                synchronized (this) {
                                    notify(); // 唤醒生产者
                                }
                            }
                        };
                        zkClient.getChildren(MAILBOX_ROOT_PATH, watcher);
                        synchronized (watcher) {
                            watcher.wait(); // 阻塞生产者
                        }
                    } else {
                        // 线程随机休眠数毫秒，模拟现实中的费时操作
                        int sleepMills = (int) (Math.random() * 1000);
                        Thread.sleep(sleepMills);

                        // 接收信件，创建新的子节点
                        String newLetterPth = zkClient.create(MAILBOX_ROOT_PATH + "/" + LETTER_NODE_NAME, "letter"
                                        .getBytes(),
                                ZooDefs.Ids
                                        .OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                        System.out.println("a new letter has been received: " + newLetterPth.substring
                                (MAILBOX_ROOT_PATH.length() + 1) + ", letter num: " + getLetterNum());
                    }
                } catch (Exception e) {
                    System.out.println("producer equit task beacuse of exception !");
                    e.printStackTrace();
                    break;
                }
            }
        }

        private int getLetterNum() throws KeeperException, InterruptedException {
            Stat stat = zkClient.exists(MAILBOX_ROOT_PATH, false);
            int letterNum = stat.getNumChildren();
            return letterNum;
        }

        public void setZkClient(ZooKeeper zkClient) {
            this.zkClient = zkClient;
        }
    }

    // 消费者线程，负责发送信件
    static class Consumer extends Thread {
        ZooKeeper zkClient;

        @Override
        public void run() {
            while (true) {
                try {
                    if (getLetterNum() == 0) {    // 信箱已为空
                        System.out.println("mailBox has been empty");
                        // 创建Watcher，监控子节点变化
                        Watcher watcher = new Watcher() {
                            public void process(WatchedEvent watchedEvent) {
                                // 消费者已停止，只有生产者在活动，所以只可能出现收取信件的动作
                                System.out.println("mailBox has been not empty");
                                synchronized (this) {
                                    notify(); // 唤醒消费者
                                }
                            }
                        };
                        zkClient.getChildren(MAILBOX_ROOT_PATH, watcher);

                        synchronized (watcher) {
                            watcher.wait(); // 阻塞消费者
                        }

                    } else {
                        // 线程随机休眠数毫秒，模拟现实中的费时操作
                        int sleepMillis = (int) (Math.random() * 1000);
                        Thread.sleep(sleepMillis);

                        // 发送信件，删除序号最小的子节点
                        String firstLetter = getFirstLetter();
                        zkClient.delete(MAILBOX_ROOT_PATH + "/" + firstLetter, -1);
                        System.out.println("a letter has been delivered: " + firstLetter
                                + ", letter num: " + getLetterNum());
                    }
                } catch (Exception e) {
                    System.out.println("consumer equit task becouse of exception !");
                    e.printStackTrace();
                    break;
                }
            }
        }

        private int getLetterNum() throws KeeperException, InterruptedException {
            Stat stat = zkClient.exists(MAILBOX_ROOT_PATH, false);
            int letterNum = stat.getNumChildren();
            return letterNum;
        }

        private String getFirstLetter() throws KeeperException, InterruptedException {
            List<String> letterPaths = zkClient.getChildren(MAILBOX_ROOT_PATH, false);
            Collections.sort(letterPaths);
            return letterPaths.get(0);
        }

        public void setZkClient(ZooKeeper zkClient) {
            this.zkClient = zkClient;
        }
    }

    public static void main(String[] args) throws IOException {
        // 开启生产者线程
        Producer producer = new Producer();
        ZooKeeper zkClientA = new ZooKeeper("192.168.0.51:2181", 3000, null);
        producer.setZkClient(zkClientA);
        producer.start();

        // 开启消费者线程
        Consumer consumer = new Consumer();
        ZooKeeper zkClientB = new ZooKeeper("192.168.0.51:2181", 3000, null);
        consumer.setZkClient(zkClientB);
        consumer.start();
    }
}