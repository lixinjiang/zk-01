package cn.lxj.bigdate.day04._04_reflect.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TestServer
 * description
 * create by lxj 2018/5/8
 **/
public class TestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost",9899));
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new TestServerTask(socket)).start();
        }
    }
}
