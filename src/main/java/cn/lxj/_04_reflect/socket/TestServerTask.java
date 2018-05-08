package cn.lxj._04_reflect.socket;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * TestServerTask
 * description 服务端做的任务
 * create by lxj 2018/5/8
 **/
public class TestServerTask implements Runnable {
    private Socket socket;

    public TestServerTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in;
        OutputStream out;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String request = br.readLine();
            String[] split = request.split(":");
            String className = split[0];
            String methodName = split[1];
            String methodParam = split[2];

            Class<?> forName = Class.forName(className);
            System.out.println("calling class: " + forName);
            Object newInstance = forName.newInstance();
            Method method = forName.getMethod(methodName, String.class);
            System.out.println("calling method: " + method);
            Object invoke = method.invoke(newInstance, methodParam);
            System.out.println("results: " + (Integer) invoke);

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(out));
            pw.println((Integer) invoke);
            pw.flush();

            br.close();
            pw.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}