package cn.peng.studygodpath.frame.rocketmq.quickstart;


import cn.peng.studygodpath.frame.rocketmq.MQQuickStart;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Producer {

    public static void main(String[] args) {
        DefaultMQProducer producer = MQQuickStart.getProducer();
        try {
            producer.start();
            // 发送消息
            for (int i = 0; i < 10; i++) {
                SendResult sendResult = producer.send(
                        new Message("quickstart", "one", "one", ("Hello World RocketMq And Consumer:" + i).getBytes()));
                System.out.println(sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

    /**
     * 同步发送消息
     */
    public static void manySend() throws Exception {
        DefaultMQProducer producer = MQQuickStart.getProducer();
        producer.setRetryTimesWhenSendFailed(3);
        SendCallback callback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("% OK %s %n", sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                System.out.printf("% Exception %s %n", e);
                e.printStackTrace();
            }
        };
        producer.start();
        for (int i = 0; i < 10; i++) {
            producer.send(new Message("quickstart", "one", "one", ("Hello World RocketMq And Consumer:" + i).getBytes()),
                    callback);
        }
        producer.shutdown();
    }

    /**
     * 异步发送消息 用于时间敏感的情况
     */
    public static void asyncSend() throws Exception {
        DefaultMQProducer producer = MQQuickStart.getProducer();

        producer.start();
        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messageList.add(
                    new Message("quickstart", "one", "one", ("Hello World RocketMq And Consumer:" + i).getBytes()));
        }
        SendResult sendResult = producer.send(messageList, 1000);
        System.out.print(sendResult);
        producer.shutdown();
    }

    /**
     * 单向发送 用于中等可靠性的情况，例如日志
     */
    public static void oneWaySend() throws Exception {
        DefaultMQProducer producer = MQQuickStart.getProducer();
        producer.start();
        producer.sendOneway(new Message("quickstart", "one", "one", ("Hello World RocketMq And Consumer: one way").getBytes()));
        producer.shutdown();
    }

}
