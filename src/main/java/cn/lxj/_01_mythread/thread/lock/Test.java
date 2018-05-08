package cn.lxj._01_mythread.thread.lock;

/**
 * Test
 * description
 * create by lxj 2018/5/7
 **/
public class Test {
    public static void main(String[] args) {
        // 获取机器的cpu核数
        int num = Runtime.getRuntime().availableProcessors();
        System.out.println(num);// 4
    }
}
