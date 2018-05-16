package cn.lxj.bigdate.day05._03_netty.sendobject.coder;

import cn.lxj.bigdate.day05._03_netty.sendobject.utils.ByteBufToBytes;
import cn.lxj.bigdate.day05._03_netty.sendobject.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * PersonDecoder
 * description 反序列化
 * create by lxj 2018/5/16
 **/
public class PersonDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws
            Exception {
        // 工具类：将ByteBuf转换为byte[]
        ByteBufToBytes read = new ByteBufToBytes();
        byte[] bytes = read.read(byteBuf);
        // 工具类：将byte[]转化为object
        Object object = ByteObjConverter.byteToObject(bytes);
        list.add(object);
    }
}
