package cn.peng.studygodpath.frame.rocketmq.order;

import cn.peng.studygodpath.frame.rocketmq.MQQuickStart;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * 顺序消费
 */
public class OrderProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = MQQuickStart.getProducer();
        producer.start();
        String[] tags = new String[]{"OrderCreate", "PayOrder", "SendOrder"};
        for (int orderId = 0; orderId < 10; orderId++) {

            for (int j = 0; j < 3; j++) {
                Message msg = new Message("quickstart", tags[j % tags.length], orderId + tags[j % tags.length],
                        ("Order Operator" + orderId).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size(); // 默认为4，工单id 对队列取余，这会使 同一订单的消息会发送到统一队列
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.printf("%s%n", sendResult);
            }
        }
        producer.shutdown();
    }

}
