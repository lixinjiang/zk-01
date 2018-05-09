package cn.lxj.bigdate.day04._06_mySerializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * SerTask
 * description 序列化对象到硬盘
 * create by lxj 2018/5/9
 **/
public class SerTask {
    public static void main(String[] args) throws IOException {
        Task t = new Task();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D://tasks"));
        oos.writeObject(t);
        oos.close();
    }
}
