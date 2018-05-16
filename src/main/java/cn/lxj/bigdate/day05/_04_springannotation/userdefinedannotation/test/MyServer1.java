package cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.test;

import cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.annotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * MyServer1
 * description TODO
 * create by lxj 2018/5/16
 **/
//@Component //ApplicationContextAware会为Component组件调用setApplicationContext方法；  测试Myserver3时注释
public class MyServer1 implements ApplicationContextAware {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/cn/lxj/bigdate/day05/spring2.xml");
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
        System.out.println(serviceBeanMap);
        for (Object serviceBean : serviceBeanMap.values()) {
            try {
                String value = serviceBean.getClass().getAnnotation(RpcService.class).value();
                System.out.println("注解上的value：" + value);

                Method method = serviceBean.getClass().getMethod("hello", new Class[]{String.class});
                Object o = method.invoke(serviceBean, "bbb");
                System.out.println(o);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
