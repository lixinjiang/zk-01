package cn.lxj.bigdate.day04._03_mq.queue;

/**
 * ProducerTest
 * description 生产者测试类
 * create by lxj 2018/5/8
 **/
public class ProducerTest {
    public static void main(String[] args) throws Exception {
        ProducerTool producer = new ProducerTool(); // 生产者
        producer.produceMessage("Hello,World!I am queue2");
        producer.close();
    }
}
