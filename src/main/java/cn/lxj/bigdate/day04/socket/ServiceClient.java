package cn.lxj.bigdate.day04.socket;

import java.io.*;
import java.net.Socket;

/**
 * ServiceClient
 * description 客户端
 * create by lxj 2018/5/7
 **/
public class ServiceClient {
    public static void main(String[] args) throws IOException {
        // 向服务器发出请求建立连接
        Socket socket = new Socket("localhost",8899);
        // 从socket中获取输入输出流
        InputStream inputstream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(outputStream);
        pw.println("hello");
        pw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputstream));
        String result = br.readLine();
        System.out.println(result);

        inputstream.close();
        outputStream.close();
        socket.close();
    }
}
