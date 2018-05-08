package cn.lxj._03_mq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ProducerTool
 * description 消息生产者
 * create by lxj 2018/5/8
 **/
public class ProducerTool {
    // 使用默认的用户名admin
    private String user = ActiveMQConnection.DEFAULT_USER;
    // 使用默认的用户密码admin
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    // 使用默认的中间件URL
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // 订阅的主题
    private String subject = "mytopic";
    // 目的地
    private Destination destination = null;
    // 连接
    private Connection connection = null;
    // session
    private Session session = null;
    // 消息生产者
    private MessageProducer producer = null;

    // 初始化
    private void initialize() throws JMSException, Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                user, password, url);   // 创建activemq的生产工厂
        connection = connectionFactory.createConnection();  // 创建连接
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);    // 创建session
        destination = session.createTopic(subject); // 发布主题，目的地
        producer = session.createProducer(destination); // 创建生产者
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  //
    }

    // 发送消息
    public void produceMessage(String message) throws JMSException, Exception {
        initialize();   // 初始化信息，得到session
        TextMessage msg = session.createTextMessage(message); // 发送消息的内容
        connection.start(); // 连接打开
        System.out.println("Producer:->Sending message: " + message);
        producer.send(msg); // 发送消息
        System.out.println("Producer:->Message sent complete!");
    }

    // 关闭连接
    public void close() throws JMSException {
        System.out.println("Producer:->Closing connection");
        if (producer != null)
            producer.close();
        if (session != null)
            session.close();
        if (connection != null)
            connection.close();
    }
}