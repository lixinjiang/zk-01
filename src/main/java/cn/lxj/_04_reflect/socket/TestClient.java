package cn.lxj._04_reflect.socket;

import java.io.*;
import java.net.Socket;

/**
 * TestClient
 * description 客户端
 * create by lxj 2018/5/8
 **/
public class TestClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9899);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(out));
        pw.println("cn.lxj._04_reflect.socket.TestBussiness:getPrice:yifu");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = br.readLine();
        System.out.println("client get result: " + readLine);
        socket.close();
    }
}
