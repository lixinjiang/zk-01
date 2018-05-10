package cn.lxj.bigdate.day05._03_netty.sendobject.utils;

import io.netty.buffer.ByteBuf;

/**
 * ByteBufToBytes
 * description
 * create by lxj 2018/5/10
 **/
public class ByteBufToBytes {
    /**
     * 将ByteBuf转化为byte[]
     *
     * @param datas
     * @return
     */
    public byte[] read(ByteBuf datas) {
        byte[] bytes = new byte[datas.readableBytes()]; // 创建byte[]
        datas.readBytes(bytes);// 将ByteBuf转化为byte[]
        return bytes;
    }
}
