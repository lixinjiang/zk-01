package cn.lxj.bigdate.day04._06_mySerializable;

import java.io.Serializable;
import java.util.Date;

/**
 * Task
 * description TODO
 * create by lxj 2018/5/9
 **/
public class Task implements Runnable, Serializable {

    private static final long serialVersionUID = -1278236106856875914L;

    @Override
    public void run() {
        System.out.println(new Date());
    }
}
