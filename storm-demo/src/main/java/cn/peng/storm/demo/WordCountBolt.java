package cn.peng.storm.demo;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2022-10-29 17:43
 * QDescription:
 */
public class WordCountBolt extends BaseRichBolt {
    private HashMap<String, Long> wordCount = null;

    @Override
    public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {
        wordCount = new HashMap<>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        wordCount.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
        System.out.println(word + "\t" + wordCount.get(word));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
