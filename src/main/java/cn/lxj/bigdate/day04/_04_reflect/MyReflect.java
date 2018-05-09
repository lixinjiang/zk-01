package cn.lxj.bigdate.day04._04_reflect;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * MyReflect
 * description 泛型测试
 * create by lxj 2018/5/8
 **/
public class MyReflect {
    public String className = null;
    public Class personClass = null;

    /**
     * 反射Person类
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        className = "cn.lxj.bigdate.day04._04_reflect.Person";
        personClass = Class.forName(className);
    }

    /**
     * 获取某个class文件对象
     *
     * @throws Exception
     */
    @Test
    public void getClassName() throws Exception {
        System.out.println(personClass);    // 并没有实例化
    }

    /**
     * 获取某个class的另一种方式
     *
     * @throws Exception
     */
    @Test
    public void getClassName2() throws Exception {
        System.out.println(Person.class);   // 并没有实例化
    }

    /**
     * 创建一个class文件表示的实例对象，底层会调用空参数的构造方法
     *
     * @throws Exception
     */
    @Test
    public void getNewInstance() throws Exception {
        System.out.println(personClass.newInstance());  // 物理地址
    }

    /**
     * 获取非私有的构造方法
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void getPublicConstructor() throws Exception {
        Constructor constructor = personClass.getConstructor(Long.class, String.class);
        Person person = (Person) constructor.newInstance(100L, "zhangsan");
        System.out.println(person.getId());
        System.out.println(person.getName());
    }

    /**
     * 获取私有的构造方法
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void getPrivateConstructor() throws Exception {
        Constructor con = personClass.getDeclaredConstructor(String.class);
        con.setAccessible(true);    // 强制取消java的权限监测
        Person person2 = (Person) con.newInstance("zhangsan");
        System.out.println("*** " + person2.getName());
    }

    /**
     * 访问非私有的成员变量
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void getNotPrivateField() throws Exception {
        Constructor constructor = personClass.getConstructor(Long.class, String.class);
        Object obj = constructor.newInstance(100L, "zhangsan");

        Field field = personClass.getField("name");
        field.set(obj, "lisi");
        System.out.println(field.get(obj));
        System.out.println((Person) obj);
    }

    /**
     * 访问私有的成员变量
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void getPrivateField() throws Exception {
        Constructor constructor = personClass.getConstructor(Long.class);
        Object obj = constructor.newInstance(100L);

        Field field2 = personClass.getDeclaredField("id");
        field2.setAccessible(true); // 强制取消java的权限检测
        field2.set(obj, 1000L);
        System.out.println(field2.get(obj));
        System.out.println((Person) obj);
    }

    /**
     * 访问非私有的成员函数
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void getPublicMethod() throws Exception {
        System.out.println(personClass.getMethod("toString"));
        Object obj = personClass.newInstance(); // 获取空参数的构造函数
        Method toStringMethod = personClass.getMethod("toString");
        // 调用方法toString的方法
        Object object = toStringMethod.invoke(obj);
        System.out.println(object);
    }

    /**
     * 1、反射当前类的直接父类
     * 2、获取到一个输入流，这个输入流会关联到name所表示的那个文件上
     * 3、判断当前的Class对象表示是否是数组
     * 4、判断当前的Class对象表示是否是枚举类
     * 5、判断当前的Class对象表示是否是接口
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void otherMethod() throws Exception {
        // 当前加载这个class文件的按个类加载器对象
        System.out.println(personClass.getClassLoader());
        // 获取某个类实现的所有接口
        Class[] interfaces = personClass.getInterfaces();
        for (Class class1 : interfaces) {
            System.out.println(class1);
        }
        // 反射当前这个类的直接父类
        System.out.println("method:getGenericSuperclass==================");
        System.out.println(personClass.getGenericSuperclass());
        /**
         * getResourceAsStream这个方法可以获取到一个输入流，这个输入流会关联到name所表示的那个文件上。
         */
        // path,不以'/'开头时候，默认是从此类所在的包下取资源，以'/'开头则是从ClassPath根下获取
        // 其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
        System.out.println("method:getResourceAsStream==================");
        System.out.println(personClass.getResourceAsStream("/log4j.properties"));//从根目录下查找
        System.out.println(personClass.getResourceAsStream("log4j.properties"));//从当前包下查找
        //判断当前的Class对象表示是否是数组
        System.out.println("method:isArray==================");
        System.out.println(personClass.isArray());
        System.out.println(new String[3].getClass().isArray());
        //判断当前的Class对象表示是否是枚举类
        System.out.println("method:isEnum==================");
        System.out.println(personClass.isEnum());
        System.out.println(Class.forName("cn.lxj.bigdate.day04._04_reflect.City").isEnum());
        //判断当前的Class对象表示是否是接口
        System.out.println("method:isInterface==================");
        System.out.println(personClass.isInterface());
        System.out.println(Class.forName("cn.lxj.bigdate.day04._04_reflect.TestInterface").isInterface());
    }
}