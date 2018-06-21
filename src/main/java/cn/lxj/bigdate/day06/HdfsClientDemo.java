package main.java.cn.lxj.bigdate.day06;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

/**
 * HdfsClientDemo
 * description TODO
 * create by lxj 2018/6/8
 **/
public class HdfsClientDemo {
    FileSystem fs = null;
    Configuration conf = null;

    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://hadoop0:9000");
        //拿到一个文件系统操作的客户端实例对象
        //fs = FileSystem.get(conf);
        //可以直接传入uri和用户身份
        //System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master");

        fs = FileSystem.get(new URI("hdfs://hadoop0:9000"),conf,"hadoop");//最后一个参数为用户名
    }

    @Test
    public void testUpload() throws Exception{
        Thread.sleep(2000);
        fs.copyFromLocalFile(new Path("D:/CrackCaptcha.log"),new Path("/CrackCaptcha.log.copy"));
        fs.close();
    }

    @Test
    public void testDownLoad() throws Exception{
        fs.copyToLocalFile(new Path("/CrackCaptcha.log.copy"),new Path("D:/"));
    }

    @Test
    public void testConf(){
        Iterator<Map.Entry<String,String>> iterator = conf.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            System.out.println(entry.getValue() + "--" + entry.getValue());//conf加载的内容
        }
    }

    /**
     * 创建目录
     * @throws Exception
     */
    @Test
    public void makdirTest() throws Exception{
        boolean mkdirs = fs.mkdirs(new Path("/aaa/bbb"));
        System.out.println(mkdirs);
    }

    /**
     * 删除
     * @throws Exception
     */
    @Test
    public void deleteTest() throws Exception{
        boolean delete = fs.delete(new Path("/aaa"),true);//true
        System.out.println(delete);
    }


    @Test
    public void listTest() throws Exception{
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus){
            System.out.println(fileStatus.getPath() + "=============" + fileStatus.toString());
        }
        //会递归找到所有的文件
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()){
            LocatedFileStatus next = listFiles.next();
            String name = next.getPath().getName();
            Path path = next.getPath();
            System.out.println(name + "===========" + path.toString());
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://hadoop0:9000");

        //拿到一个文件系统操作的客户端实例对象
//        FileSystem fs = FileSystem.get(conf);
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop0:9000"), conf, "hadoop");
        //fs.copyFromLocalFile(new Path("D:/CrackCaptcha.log"),new Path("/CrackCaptcha.log.copy"));
        fs.copyFromLocalFile(new Path("D:/angelababy.love"),new Path("/angelababy.love"));
        fs.close();
    }
}