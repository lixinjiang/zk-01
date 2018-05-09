package cn.lxj.bigdate.day04._05_proxy.proxyclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyBoss
 * description TODO
 * create by lxj 2018/5/9
 **/
public class ProxyBoss {
    /**
     * 对接口方法进行代理
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final int discountCoupon, final Class<?> interfaceClass, final Class<?>
            implmentsClass) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new
                InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 调用原始对象以后返回的值
                        Integer returnValue = (Integer) method.invoke(implmentsClass.newInstance(), args);
                        return returnValue - discountCoupon;
                    }
                });
    }
}