package cn.lxj.bigdate.day05._04_springannotation.springrunorder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Four
 * description TODO
 * create by lxj 2018/6/1
 **/
public class Four implements ApplicationContextAware,InitializingBean{

    public Four (String four){
        System.out.println(four);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet:Four");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext:Four");
    }
}
