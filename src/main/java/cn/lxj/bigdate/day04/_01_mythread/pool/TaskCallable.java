package cn.lxj.bigdate.day04._01_mythread.pool;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * TaskCallable
 * description
 * create by lxj 2018/5/7
 **/
public class TaskCallable implements Callable<String> {
    private int a;
    Random r = new Random();

    public TaskCallable(int a) {
        this.a = a;
    }

    @Override
    public String call() throws Exception {
        String name = Thread.currentThread().getName();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(name + " 启动时间：" + currentTimeMillis / 1000);
        int rint = r.nextInt(3);
        try {
            Thread.sleep(rint * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " is working ... " + a);
        return a + "";
    }
}
