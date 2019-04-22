package cn.peng.studygodpath.frame.rocketmq.order;

import cn.peng.studygodpath.frame.rocketmq.MQQuickStart;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class OrderConsumer {
    public static void main(String[] args) {
        try {
            DefaultMQPushConsumer consumer = MQQuickStart.getPushConsumer();

            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setConsumeThreadMin(10);
            consumer.setConsumeThreadMax(20);
            consumer.subscribe("quickstart", "*");

            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                           ConsumeOrderlyContext context) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
            System.out.printf("Consumer Started.%n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
