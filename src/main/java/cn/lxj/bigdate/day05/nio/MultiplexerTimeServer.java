package cn.lxj.bigdate.day05.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * MultiplexerTimeServer
 * description NIO编程,多路复用器
 * create by lxj 2018/5/10
 **/
public class MultiplexerTimeServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    /**
     * 初始化多路复用器、绑定监听端口
     * 构造方法
     *
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            serverSocketChannel = ServerSocketChannel.open();   // 新建NIO通道，学校
            serverSocketChannel.configureBlocking(false);   // 设置为非阻塞，与Selector一起使用时，Channel必须处于非阻塞模式下
            selector = Selector.open(); //选择器创建，教务处
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);   // 创建基于NIO通道的socket连接   新建socket通道的端口
            // ServerSocket，ss注册了选择器之后，其下的老师ServerSocket也就加入了员工名单了，所以老师的编号是skey
            SelectionKey skey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);// 将NIO通道选绑定到择器,当然绑定后分配的主键为skey 将选择器和通道注册到内核
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (!stop) {  // 如果服务未停止
            try {
                selector.select(1000);  // 设置选择器超时时间
                // 获取通道内关心事件的集合 ，这里的集合就是老师和学生的编号集合，
                // 如果key是学生的，那就是老学生来问问题，如果key是老师的，那就是招生办的老师带着一个新生来注册
                // 选择器内包含的channelkey
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();// 迭代器
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();  // 先得到这个学生的编号key
                    iterator.remove();  // 正在处理这个key，那么将这个key在迭代器中移除
                    // 处理逻辑
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理输出流
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {
                // 接收新的连接
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);    // 设置为非阻塞
                // 添加新的connect连接到selector选择器
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                // read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");   // string的反序列化
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System
                            .currentTimeMillis()).toString() : "BAD ORDER";
                    // 写出数据
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    ; // 读到0字节，忽略
                }
            }
        }
    }

    /**
     * 写回客户机
     *
     * @param channel
     * @throws IOException
     */
    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}