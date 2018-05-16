package cn.lxj.bigdate.day05._10_callback;

/**
 * Date
 * description TODO
 * create by lxj 2018/5/16
 **/
public class Data {
    private int n;
    private int m;

    public Data(int n, int m) {
        System.out.println("调用date的构造函数");
        this.n = n;
        this.m = m;
    }

    @Override
    public String toString() {
        int r = n / m;
        return n + "/" + m + " = " + r;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
}
