package cn.lxj.zk01;

/**
 * MultiThreadTest
 * description 多线程中的锁控制
 * create by lxj 2018/5/3
 **/
public class MultiThreadTest {
    // 以一个静态变量来模拟公共资源，未对公共资源进行控制，可能会发生对公共资源的改动时候的问题
    // 执行结果是：
    // threadA执行完毕：84
    // threadC执行完毕：100
    // threadB执行完毕：98
    // threadD执行完毕：98
    // threadE执行完毕：96

    // 给plus方法加synchronized关键字，重新运行后的出的结果
    // threadA执行完毕：20
    // threadE执行完毕：40
    // threadD执行完毕：60
    // threadC执行完毕：80
    // threadB执行完毕：100
    private static int counter = 0;

    // 多线程环境下会出现并发问题
    static void plus() {
        // 计数器加一
        counter++;
        // 线程随机休眠数毫秒，模拟现实中的耗时操作,执行的业务
        int sleepMillis = (int) Math.random() * 100;
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程实现类
     */
    static class CountPlus extends Thread {
        @Override
        public void run() {
            synchronized (MultiThreadTest.class) {
                for (int i = 0; i < 20; i++) {
                    // plus加二十次
                    plus();
                }
                System.out.println(Thread.currentThread().getName() + "执行完毕：" + counter);
            }
        }

        CountPlus(String threadName) {
            super(threadName);
        }
    }

    public static void main(String[] args) {
        CountPlus threadA = new CountPlus("threadA");
        threadA.start();

        CountPlus threadB = new CountPlus("threadB");
        threadB.start();

        CountPlus threadC = new CountPlus("threadC");
        threadC.start();

        CountPlus threadD = new CountPlus("threadD");
        threadD.start();

        CountPlus threadE = new CountPlus("threadE");
        threadE.start();
    }
}