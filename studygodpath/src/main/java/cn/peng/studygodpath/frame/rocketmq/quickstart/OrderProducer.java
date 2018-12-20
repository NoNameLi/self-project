package cn.peng.studygodpath.frame.rocketmq.quickstart;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 有序消息生成者
 */
public class OrderProducer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("order-producer");
        producer.setNamesrvAddr("192.168.95.128:9876;192.168.95.129:9876");
        try {
            producer.start();

            // 发送消息
            for (int i = 0; i < 90; i++) {
                SendResult sendResult = producer.send(
                        new Message("quickstart", "one", "one", ("Hello World RocketMq And Consumer:" + i).getBytes()));
                System.out.println(sendResult);
            }

            for (int i = 0; i < 10; i++) {
                Message message = new Message("quickstart", "batch", "batch",
                        ("Hello World RocketMq And Consumer:" + i).getBytes());
                // RocketMQ通过MessageQueueSelector中实现的算法来确定消息发送到哪一个队列上
                // RocketMQ默认提供了两种MessageQueueSelector实现：随机/Hash
                // 当然你可以根据业务实现自己的MessageQueueSelector来决定消息按照何种策略发送到消息队列中
                // 一般同一个OrderId获取到的肯定是同一个队列。
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, i);
                System.out.println(sendResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }


}
