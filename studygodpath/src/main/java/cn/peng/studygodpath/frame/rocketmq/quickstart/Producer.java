package cn.peng.studygodpath.frame.rocketmq.quickstart;


import cn.peng.studygodpath.frame.rocketmq.MQQuickStart;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

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

//            List<Message> msgs = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                msgs.add(new Message("quickstart", "batch", "batch",
//                        ("Hello World RocketMq And Consumer:" + i).getBytes()));
//            }
            // 同步发送消息
//            SendResult sendResult = producer.send(msgs);
            // 异步发送消息 用于时间敏感的情况
//            producer.send(msgs, new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.printf("%-10d OK %s %n", i, sendResult.getMsgId());
//                }
//                @Override
//                public void onException(Throwable e) {
//                    System.out.printf("%-10d Exception %s %n", i, e);
//                    e.printStackTrace();
//                }
//            });
            // 单向发送 用于中等可靠性的情况，例如日志
//            producer.sendOneway(msgs);

//            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }


}
