package cn.peng.studygodpath.frame.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;

public class MQQuickStart {

    public static DefaultMQProducer getProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("quickstart-producer");
        producer.setNamesrvAddr("192.168.95.128:9876;192.168.95.129:9876");
//        producer.setNamesrvAddr("192.168.184.129:9876");
        return producer;
    }

    public static TransactionMQProducer getTransactionProducer() {
        TransactionMQProducer producer = new TransactionMQProducer("transcation-producer");
        return producer;
    }

    public static DefaultMQPushConsumer getPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("quickstart-consumer");
//        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("quickstart-consumer");
        consumer.setNamesrvAddr("192.168.95.128:9876;192.168.95.129:9876");
//        consumer.setNamesrvAddr("192.168.184.129:9876");
        return consumer;
    }


}
