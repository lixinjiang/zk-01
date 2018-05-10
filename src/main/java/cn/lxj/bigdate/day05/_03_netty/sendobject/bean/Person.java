package cn.lxj.bigdate.day05._03_netty.sendobject.bean;

import java.io.Serializable;

/**
 * Person
 * description
 * create by lxj 2018/5/10
 **/
public class Person implements Serializable {
    private static final long serialVersionUID = -5941514447637207455L;
    private String name;
    private String sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name: " + name + " sex:" + sex + " age:" + age;
    }
}