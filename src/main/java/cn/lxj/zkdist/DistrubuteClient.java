package cn.lxj.zkdist;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DistrubuteClient
 * description 分布式客户端
 * create by lxj 2018/5/5
 **/
public class DistrubuteClient {
    private ZooKeeper zk = null;
    private static final String connectString = "192.168.0.51:2181";
    private static final int sessionTimeout = 2000;
    private static final String parentNode = "/servers";
    // 加volatile的意义，保证这个list不能被拷贝，进行修改或者操作的时候哦，直接在对象所在的堆进行更改，保证数据的同步性
    private volatile List<String> serverList;

    /**
     * 创建zk的客户端连接
     * @throws IOException
     */
    public void getConnect() throws IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                // 收到时间通知后的回调函数（应该是我们自己的事件处理逻辑）
                try {
                    // 重新更新服务器列表，并且注册了监听
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 获取服务器信息列表
    public void getServerList() throws KeeperException, InterruptedException {
        // 获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zk.getChildren(parentNode, true);

        // 先创建一个局部的list来存放服务器信息
        ArrayList<String> servers = new ArrayList<String>();
        for (String child : children) {
            byte[] data = zk.getData(parentNode + "/" + child, false, null);
            servers.add(new String(data));
        }
        // 将servers赋值给成员变量serverlist，提供给业务线程使用
        serverList = servers;

        // 打印服务器列表
        System.out.println(serverList.toString());
    }

    /**
     * 业务逻辑
     *
     * @throws Exception
     */
    public void handleBussiness() throws Exception {
        System.out.println("client start working ......");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 获取zk连接
        DistrubuteClient client = new DistrubuteClient();
        client.getConnect();
        // 获取servers的子节点信息（并监听），从中获取服务信息列表
        client.getServerList();
        Thread.sleep(Long.MAX_VALUE);
    }
}
