package cn.lxj.bigdate.day05._03_netty.sendobject.client;

import cn.lxj.bigdate.day05._03_netty.sendobject.bean.Person;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * EchoClientHandler
 * description 客户端处理器映射器,连接，接收，捕捉异常
 * create by lxj 2018/5/16
 **/
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    // 客户端连接服务器后被调用
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        Person person = new Person();
        person.setAge(18);
        person.setName("zhangsan");
        person.setSex("girl");
        channelHandlerContext.write(person);
        channelHandlerContext.flush();
    }

    // 从服务器接收到数据后调用
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("client 读取server数据...");
        // 服务端返回消息后
        ByteBuf buf = (ByteBuf) byteBuf;
        // 服务端返回消息后
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端数据为：" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("client exceptionCaught...");
        // 释放资源
        ctx.close();
    }
}