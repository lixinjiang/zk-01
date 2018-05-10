package cn.lxj.bigdate.day05._02_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * TransferToServer
 * description TODO
 * create by lxj 2018/5/10
 **/
public class TransferToServer {
    public static void main(String[] args) throws IOException {
        // 创建socket channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.setReuseAddress(true);// 地址重用
        socket.bind(new InetSocketAddress("localhost", 9026));// 绑定地址
        System.out.println("监听端口： " + new InetSocketAddress("localhost", 9026).toString());
        // 分配一个新的字节缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(4096);
        // 读取数据
        while (true){
            SocketChannel channel = serverSocketChannel.accept(); // 接收数据
            System.out.println("Accepted : " + channel);
            channel.configureBlocking(true);//设置为阻塞，接收不到就停
            int nread = 0;
            while (nread != -1){
                try {
                    nread = channel.read(allocate);//往缓冲区里读
                    byte[] array = allocate.array();//将数据转化为array
                    // 打印
                    String s = new String(array, 0, allocate.position());
                    System.out.println(s);
                    allocate.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                    nread = -1;
                }
            }
        }
    }
}