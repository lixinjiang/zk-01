package cn.lxj.zkdist;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * DistributeServer
 * description 分布式服务器端
 * create by lxj 2018/5/5
 **/
public class DistributeServer {

    private ZooKeeper zk = null;
    private static final String connectString = "192.168.0.51:2181";
    private static final int sessionTimeout = 2000;
    private static final String parentNode = "/servers";

    /**
     * 创建zk的客户端连接
     * @throws IOException
     */
    public void getConnect() throws IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

                // 收到时间通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(watchedEvent.getType() + "---" + watchedEvent.getPath());
                try {
                    // 监听只能监听一次就失效，所以需要再次增加监听
                    zk.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 向zk集群注册服务器注册信息,注册的意思就是向zk集群添加节点
     * @param hostname
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void registerServer(String hostname) throws KeeperException, InterruptedException {
        String create = zk.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode
                .EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + " is online... " + create);
    }

    /**
     * 业务功能
     * @param hostname
     * @throws InterruptedException
     */
    public void handleBusiness(String hostname) throws InterruptedException {
        System.out.println(hostname + " start working...");
        Thread.sleep(Long.MAX_VALUE);
    }

    // 伪代码
    public static void main(String[] args) throws Exception {
        // 获取zk连接
        DistributeServer server = new DistributeServer();
        server.getConnect();
        // 利用zk连接注册服务信息
        server.registerServer(args[0]);
        // 业务功能
        server.handleBusiness(args[0]);
    }
}