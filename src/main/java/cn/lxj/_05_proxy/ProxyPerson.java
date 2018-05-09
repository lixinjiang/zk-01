package cn.lxj._05_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyPerson
 * description 代理
 * create by lxj 2018/5/9
 **/
public class ProxyPerson {
    public static void main(String[] args) {
        final MyPerson p = new MyPerson();
        PersonInterface proxy = (PersonInterface) Proxy.newProxyInstance(MyPerson.class.getClassLoader(), MyPerson
                .class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Proxy is comming... ");

                if (method.getName() == "saySomeThing") {
                    System.out.println("say some thing is special handled... ");
                    p.saySomeThing();
                } else {
                    Object invoke = method.invoke(p, args);  // 调用任何public方法都拦截
                    System.out.println("proxy is leaving... ");
                }
                return null;
            }
        });
        // 代理对象调用
        proxy.doSomeThing();
        proxy.saySomeThing();
    }
}