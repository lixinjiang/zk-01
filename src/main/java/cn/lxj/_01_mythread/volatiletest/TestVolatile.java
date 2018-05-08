package cn.lxj._01_mythread.volatiletest;

/**
 * TestVolatile
 * description Volatile关键字测试,从结果看出volatile关键字并不是线程安全的
 * create by lxj 2018/5/7
 **/
public class TestVolatile {
    public static volatile int numb = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        numb++;
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(numb);
    }
}