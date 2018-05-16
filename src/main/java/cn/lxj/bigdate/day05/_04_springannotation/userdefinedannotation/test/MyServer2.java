package cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.test;

import cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.annotation.RpcService;
import cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * MyServer2
 * description
 * create by lxj 2018/5/16
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:cn/lxj/bigdate/day05/spring2.xml")
//@Component
public class MyServer2 implements ApplicationContextAware{
    @SuppressWarnings("resources")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/cn/lxj/bigdate/day05/spring2.xml");
        Map<String, Object> beans = ctx.getBeansWithAnnotation(RpcService.class);
        for(Object obj: beans.values()){
            HelloService hello=(HelloService) obj;
            String hello2 = hello.hello("mmmm");
            System.out.println(hello2);
        }
    }

    @Test
    public void helloTest1() {
        System.out.println("开始junit测试……");
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx
                .getBeansWithAnnotation(RpcService.class);
        for (Object serviceBean : serviceBeanMap.values()) {
            try {
                Method method = serviceBean.getClass().getMethod("hello",
                        new Class[] { String.class });
                Object invoke = method.invoke(serviceBean, "bbb");
                System.out.println(invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
