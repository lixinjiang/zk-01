package cn.lxj.bigdate.Mail;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * SendMsg_webchinese
 * description 短讯发送接口
 * create by lxj 2018/5/6
 **/
public class SendMsg_webchinese {
    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", "bonusli"), // 注册的用户名
//                c54fa7f547a1ee01b481
                new NameValuePair("Key", "c54fa7f547a1ee01b481"), // 注册成功后,登录网站使用的密钥
//                new NameValuePair("smsMob", "15201509910"), // 手机号码
                new NameValuePair("smsMob", "17682315906"), // 手机号码
                new NameValuePair("smsText", "晚上吃鸡蛋？") };//设置短信内容
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes(
                "gbk"));
        System.out.println(result);
        post.releaseConnection();
    }
}
