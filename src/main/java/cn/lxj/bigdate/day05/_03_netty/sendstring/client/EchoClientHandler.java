package cn.lxj.bigdate.day05._03_netty.sendstring.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * EchoClientHandler
 * description
 * create by lxj 2018/5/10
 **/
public class EchoClientHandler extends SimpleChannelInboundHandler {
    /**
     * 客户端连接服务器后被调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接服务器，开始发送数据... ");
        byte[] req = "QUERY TIME ORDER".getBytes();//消息
        ByteBuf firstMessage = Unpooled.buffer(req.length);//发送类
        firstMessage.writeBytes(req);//发送
        ctx.writeAndFlush(firstMessage);//flush
    }

    /**
     * 从服务器接收到数据后调用
     *
     * @param o
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        System.out.println("client 读取server数据... ");
        // 服务端返回消息后
        ByteBuf buf = (ByteBuf) o;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端数据为： " + body);
    }

    /**
     * 发生异常时候调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exceptionCaught... ");
        // 释放资源
        ctx.close();
    }
}