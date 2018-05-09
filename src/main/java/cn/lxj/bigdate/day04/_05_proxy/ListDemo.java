package cn.lxj.bigdate.day04._05_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * ListDemo
 * description TODO
 * create by lxj 2018/5/9
 **/
public class ListDemo {

    public static void main(String[] args) throws Exception {
        final List list = new ArrayList();
        // 这是被代理的
        Object oo = Proxy.newProxyInstance(List.class.getClassLoader(), list.getClass().getInterfaces(), new
                InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("加入一个对象：");
                        Object returnValue = method.invoke(list, args);  // 反射
                        System.out.println("加入完成... ");
                        if (method.getName().equals("size")) {
                            return 100;
                        }
                        return returnValue;
                    }
                });
        List list2 = (List) oo;
        list2.add("aaa");
        list2.add("bbb");
        System.out.println("size: " + list2.size() + "," + list.size());//100,2
        // 为什么调用三次？
    }   // size方法虽然也被加强了，但是size方法并没有往集合内添加元素
}
