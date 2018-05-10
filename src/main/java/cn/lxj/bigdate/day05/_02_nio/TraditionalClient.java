package cn.lxj.bigdate.day05._02_nio;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * TraditionalClient
 * description
 * create by lxj 2018/5/10
 **/
public class TraditionalClient {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        // 创建socket连接
        Socket socket = new Socket("localhost", 2000);
        System.out.println("Connected with server " + socket.getInetAddress() + ": " + socket.getPort());
        // 读取文件
        FileInputStream inputStream = new FileInputStream("D://tasks");
        // 输出文件
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        // 缓冲区4096k
        byte[] b = new byte[4096];
        // 传输长度
        long read = 0, total = 0;
        // 读取文件，写到socketio中
        while ((read = inputStream.read(b)) >= 0) {
            total = total + read;
            outputStream.write(b);
        }
        // 关闭
        outputStream.close();
        socket.close();
        inputStream.close();
        // 打印时间
        System.out.println("bytes send-- " + total + " and total time--" + (System.currentTimeMillis() - start));
    }
}