package cn.lxj._05_proxy.action;

import cn.lxj._05_proxy.proxyclass.ProxyBoss;
import cn.lxj._05_proxy.service.IBoss;
import cn.lxj._05_proxy.service.impl.Boss;
import org.junit.Test;

/**
 * ProxySaleAction
 * description 什么是动态代理？简单写个模板接口，剩下的个性化的工作，交给动态代理来完成
 * create by lxj 2018/5/9
 **/
public class ProxySaleAction {
    /**
     * 使用代理，在这个代理中，只代理了Boss的yifu方法
     * 定制化业务，可以改变原接口的参数、返回值等
     * @throws Exception
     */
    @Test
    public void saleByProxy() throws Exception{
        IBoss boss = ProxyBoss.getProxy(10,IBoss.class, Boss.class);
        // IBoss boss = new Boss();//将代理的方法实例化成接口
        System.out.println("代理经营！");
        int money = boss.yifu("xxl");   // 调用接口的方法，实际上调用方式没有变
        System.out.println("衣服成交价： " + money);
    }
}