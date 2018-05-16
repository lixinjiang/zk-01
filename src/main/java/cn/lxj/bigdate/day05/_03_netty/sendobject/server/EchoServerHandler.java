package cn.lxj.bigdate.day05._03_netty.sendobject.server;

import cn.lxj.bigdate.day05._03_netty.sendobject.bean.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * EchoServerHandler
 * description 处理器
 * create by lxj 2018/5/16
 **/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        Person person = (Person) msg;
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getSex());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("Server 读取数据完毕...");
        ctx.flush();// 刷新后才将数据发出到SocketChannel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
