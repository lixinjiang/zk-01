package cn.lxj.bigdate.day05._03_netty.sendobject.utils;

import java.io.*;

/**
 * ByteObjConverter 转换器
 * description
 * create by lxj 2018/5/10
 **/
public class ByteObjConverter {
    /**
     * 使用IO的inputstream流将byte[]转换为object
     * @param bytes
     * @return
     */
    public static Object byteToObject(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi); // 读通道
            obj = oi.readObject();  // 转化为对象
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * 使用IO的outputstream流将object转换为byte[]
     * @param obj
     * @return
     */
    public static byte[] objectToByte(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}