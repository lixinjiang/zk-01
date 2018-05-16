package cn.lxj.bigdate.day05._10_callback;

/**
 * MyFetcher
 * description TODO
 * create by lxj 2018/5/16
 **/
public class MyFetcher implements Fetcher {
    final Data data;

    /**
     * 构造方法
     * @param data
     */
    public MyFetcher(Data data) {
        System.out.println("调用MyFetcher的构造函数");
        this.data = data;
    }

    /**
     * 处理数据时回调，传入一个回调接口
     * @param callback
     */
    @Override
    public void fetchDate(FetcherCallback callback) {
        try {
            // 正常情况
            System.out.println("调用fetchData方法正常");
            callback.onData(data);
        } catch (Exception e) {
            // 报错情况
            e.printStackTrace();
            callback.onError(e);
        }
    }
}