package cn.lxj.bigdate.day05._04_springannotation.springrunorder;

import org.springframework.stereotype.Component;

/**
 * Three
 * description TODO
 * create by lxj 2018/5/16
 **/
@Component
public class Three {
    public Three(String three) {
        System.out.println(three);
    }
    public Three(){
        System.out.println("three");
    }
}
