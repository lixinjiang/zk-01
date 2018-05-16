package cn.lxj.bigdate.day05._10_callback;

public interface FetcherCallback {
    void onData(Data data) throws Exception;

    void onError(Throwable cause);
}
