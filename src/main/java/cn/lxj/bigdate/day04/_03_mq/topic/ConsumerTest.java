package cn.lxj.bigdate.day04._03_mq.topic;

import javax.jms.JMSException;

/**
 * ConsumerTest
 * description 消费者
 * create by lxj 2018/5/8
 **/
public class ConsumerTest implements Runnable {
    static Thread t1 = null;

    /**
     * @param args
     * @throws InterruptedException
     * @throws InterruptedException
     * @throws JMSException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        t1 = new Thread(new ConsumerTest());
        t1.setDaemon(false);    // 守护线程
        t1.start();
        /**
         * 如果发生异常，则重启consumer
         */
		while (true) {
			System.out.println(t1.isAlive());
			if (!t1.isAlive()) {
				t1 = new Thread(new ConsumerTest());
				t1.start();
				System.out.println("重新启动");
			}
			Thread.sleep(5000);
		}
        // 延时500毫秒之后停止接受消息
        // Thread.sleep(500);
        // consumer.close();
    }

    public void run() {
        try {
            ConsumerTool consumer = new ConsumerTool();
            consumer.consumeMessage();
            while (ConsumerTool.isconnection) {
//                consumer.onMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
