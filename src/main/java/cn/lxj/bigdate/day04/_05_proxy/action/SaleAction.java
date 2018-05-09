package cn.lxj.bigdate.day04._05_proxy.action;

import cn.lxj.bigdate.day04._05_proxy.service.IBoss;
import cn.lxj.bigdate.day04._05_proxy.service.impl.Boss;
import org.junit.Test;

/**
 * SaleAction
 * description TODO
 * create by lxj 2018/5/9
 **/
public class SaleAction {
    /**
     * 不使用代理，直接调用方法
     * 方法中规定什么业务，就只能调用什么业务，规定什么返回值，就只能输出什么返回值
     */
    @Test
    public void saleByBossSelf() throws Exception {
        IBoss boss = new Boss();
        System.out.println("老板自营！");
        int money = boss.yifu("xxl");// 老板自己卖衣服，不需要客服，结果就是没有聊天记录
        System.out.println("衣服成交价：" + money);
    }
}
