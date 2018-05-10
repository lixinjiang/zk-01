package cn.lxj.bigdate.day05.nio;

/**
 * TimeServer
 * description 时间获取的服务器启动类
 * create by lxj 2018/5/10
 **/
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length < 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认的值
                e.printStackTrace();
            }
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}