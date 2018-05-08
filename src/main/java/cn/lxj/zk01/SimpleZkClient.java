package cn.lxj.zk01;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * SimpleZkClient
 * description 单例的zk示例
 * create by lxj 2018/5/2
 **/
public class SimpleZkClient {
    //    private static final String connectString = "192.168.0.51:2181,192.168.0.52:2181,192.168.0.53:2181";
    private static final String connectString = "192.168.0.51:2181";
    private static final int sessionTimeout = 2000;
    ZooKeeper zkClient = null;

    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

                // 收到时间通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(watchedEvent.getType() + "---" + watchedEvent.getPath());
                try {
                    // 监听只能监听一次就失效，所以需要再次增加监听
                    zkClient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 数据的增删改查
     * 参数一：要创建的节点路径
     * 参数二：节点的数据
     * 参数三：节点的权限
     * 参数四：节点的类型
     *
     * @throws IOException
     * @throws KeeperException
     * @throws InterruptedException
     */

    public void testCreate() throws KeeperException, InterruptedException {

        String nodeCreated = zkClient.create("/idea", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode
                .PERSISTENT);
        // 上传的数据可以是任何类型，但是都必须转为byte类型
    }

    //获取子节点
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    // 判断znode是否存在
    @Test
    public void testExist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/idea", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }

    // 获取zonde数据
    @Test
    public void getData() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        byte[] data = zkClient.getData("/idea", false, null);
        System.out.println(new String(data, "utf-8"));
    }

    // 删除znode
    @Test
    public void deleteZnode() throws KeeperException, InterruptedException {
        // -1表示删除所有版本
        zkClient.delete("/idea", -1);
    }

    // 修改
    @Test
    public void updateZnode() throws KeeperException, InterruptedException {
        zkClient.setData("/idea", "new data".getBytes(), -1);
        byte[] data = zkClient.getData("/idea", false, null);
        System.out.println(new String(data));
    }

}
