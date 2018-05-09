package cn.lxj.bigdate.day04._05_proxy;

/**
 * MyPerson
 * description TODO
 * create by lxj 2018/5/9
 **/
public class MyPerson implements PersonInterface{

    @Override
    public void doSomeThing() {
        System.out.println("MyPerson is doing its thing... ");
    }

    @Override
    public void saySomeThing() {
        System.out.println("MyPerson is saying its thing... ");
    }

    private void xx(){
        System.out.println("MyPerson is xx its thing... ");
    }
}
