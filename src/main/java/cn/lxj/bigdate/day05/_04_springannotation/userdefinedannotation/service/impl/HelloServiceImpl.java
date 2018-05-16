package cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.service.impl;

import cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.annotation.RpcService;
import cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.service.HelloService;

/**
 * HelloServiceImpl
 * description
 * create by lxj 2018/5/16
 **/
@RpcService("HelloServicebb")
public class HelloServiceImpl implements HelloService{

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    public void test(){
        System.out.println("test");
    }
}
