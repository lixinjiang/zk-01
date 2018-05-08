package cn.lxj._04_reflect.socket;

import org.junit.Test;

/**
 * TestBussiness
 * description
 * create by lxj 2018/5/8
 **/
public class TestBussiness implements IBusiness {

    /**获取商品价格，衣服10，其他20
     * @param good
     * @return
     */
    public int getPrice(String good) {
        return good.equals("yifu") ? 10 : 20;
    }
}
