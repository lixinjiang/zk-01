package cn.lxj.bigdate.day04._06_mySerializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DesTask
 * description 从硬盘中反序列化对象
 * create by lxj 2018/5/9
 **/
public class DesTask {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D://tasks"));
        ExecutorService pool = Executors.newCachedThreadPool();
        Task t = (Task) ois.readObject();
        pool.execute(t);    // 打印出时间
        ois.close();
        pool.shutdown();
    }
}