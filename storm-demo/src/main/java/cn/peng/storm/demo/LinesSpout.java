package cn.peng.storm.demo;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;

/**
 * @Author: Administrator
 * @CreateTime:2022-10-29 16:07
 * QDescription:
 */
public class LinesSpout extends BaseRichSpout {

    private SpoutOutputCollector out;
    private String[] lines = {"Hello Storm","Hello Kafka","Hello Spark"};


    @Override
    public void open(Map<String, Object> map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        out = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        Utils.sleep(500);
        int num = new Random().nextInt(lines.length);
        String line = lines[num];
        out.emit(new Values(line));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }
}
