package cn.lxj.bigdate.day05._03_netty.sendobject.coder;

import cn.lxj.bigdate.day05._03_netty.sendobject.bean.Person;
import cn.lxj.bigdate.day05._03_netty.sendobject.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * PersonEncoder
 * description 序列化，将object转换成Byte[]
 * create by lxj 2018/5/16
 **/
public class PersonEncoder extends MessageToByteEncoder<Person> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Person msg, ByteBuf byteBuf) throws
            Exception {
        // 工具类，将Object转换为byte[]
        byte[] bytes = ByteObjConverter.objectToByte(msg);
        byteBuf.writeBytes(bytes);
        channelHandlerContext.flush();
    }
}
