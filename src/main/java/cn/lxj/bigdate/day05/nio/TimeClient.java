package cn.lxj.bigdate.day05.nio;

import java.sql.Time;

/**
 * TimeClient
 * description 客户机
 * create by lxj 2018/5/10
 **/
public class TimeClient {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 默认端口号
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}