package cn.lxj._05_proxy.service.impl;

import cn.lxj._05_proxy.service.IBoss;

/**
 * Boss
 * description 实现了卖衣服的接口
 * 自定义了自己的业务，卖裤子
 * create by lxj 2018/5/9
 **/
public class Boss implements IBoss {
    @Override
    public int yifu(String size) {
        System.out.println("天猫小强旗舰店，老板给客户发快递----衣服型号：" + size);
        // 衣服价格，从数据库读取
        return 50;
    }

    public void kuzi() {
        System.out.println("天猫小强旗舰店，老板给客户发快递----裤子");
    }
}
