package cn.lxj.bigdate.day04._03_mq.topic;

import java.util.Random;

/**
 * ProducerTest
 * description 生产者
 * create by lxj 2018/5/8
 **/
public class ProducerTest {
    public static void main(String[] args) throws Exception {
        ProducerTool producer = new ProducerTool();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(random.nextInt(10) * 100);
            producer.produceMessage("Hello, world!--" + i);
            producer.close();
        }
        System.out.println("消息已发送完毕！");
    }
}