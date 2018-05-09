package cn.lxj.bigdate.day04._03_jvm.demo;

import java.util.ArrayList;

/**
 * 64kb/50毫秒,内存溢出，当堆内存满了的时候，才回进行gc，释放资源
 * jstat的监视功能，运行时设置虚机参数为：-Xms100m -Xmx100m -XX:+UseSerialGC
 * 这段代码的作用是以64kb/50毫秒的速度往java堆内存中填充数据
 */
public class TestMemory {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws Exception {
        ArrayList<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(10000);
        fillHeap(1000);
        Thread.sleep(20000000);
    }
}
