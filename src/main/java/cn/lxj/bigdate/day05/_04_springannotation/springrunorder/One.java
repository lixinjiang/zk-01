package cn.lxj.bigdate.day05._04_springannotation.springrunorder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * One
 * description TODO
 * create by lxj 2018/5/16
 **/
public class One implements ApplicationContextAware, InitializingBean {

    public One(String one) {
        System.out.println(one);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }
}