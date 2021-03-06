package cn.lxj.bigdate.day04._01_mythread.pool;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * TestPool
 * description 测试线程池
 * create by lxj 2018/5/7
 **/
public class TestPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<?> submit = null;
        Random random = new Random();

        // 创建固定数量的线程池,4个
//        ExecutorService exec = Executors.newFixedThreadPool(4);

        // 创建调度线程池
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(4);

        // 用来记录各个线程的返回结果
        ArrayList<Future<?>> results = new ArrayList<Future<?>>();

        for (int i = 0; i < 10; i++) {
            // fixedPool提交线程，runnable无返回值，callable有返回值
            /*submit = exec.submit(new TaskRunnable(i));*/
            /*submit = exec.submit(new TaskCallable(i));*/
            //对于schedulerPool来说，调用submit提交任务时，跟普通pool效果一致
            /*submit = exec.submit(new TaskCallable(i));*/
            //对于schedulerPool来说，调用schedule提交任务时，则可按延迟，按间隔时长来调度线程的运行
            submit = exec.schedule(new TaskCallable(i), random.nextInt(10), TimeUnit.SECONDS);
            //存储线程执行结果
            results.add(submit);
        }

        for (Future f : results) {
            boolean done = f.isDone();
            System.out.println(done ? "已完成" : "未完成");//从结果的打印顺序可以看到，即使未完成，也会阻塞等待
            System.out.println("线程返回future结果： " + f.get());
        }
        exec.shutdown();
    }
}
