package cn.lxj._04_reflect;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Person
 * description
 * create by lxj 2018/5/8
 **/
public class Person implements Serializable, TestInterface {
    private Long id;
    public String name;

    /**
     * 无参构造，默认初始
     */
    public Person() {
        this.id = 100L;
        this.name = "afsdfasd";
    }

    /**
     * 含参构造
     *
     * @param id
     * @param name
     */
    public Person(Long id, String name) {
//		super();
        this.id = id;
        this.name = name;
    }

    /**
     * 含参构造
     *
     * @param id
     */
    public Person(Long id) {
        super();
        this.id = id;
    }

    /**
     * 私有的构造方法
     * @param name
     */
    @SuppressWarnings("unused")
    private Person(String name) {
        super();
        this.name = name + "=======";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }

    private String getSomeThing() {
        return "sdsadasdsasd";
    }

    private void testPrivate() {
        System.out.println("this is a private method");
    }

}