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

    private int show = 0;

    @Override
    public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {
        wordCount = new HashMap<>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        wordCount.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
        show = (show + 1) % 5;
        if (show == 0) {
            System.out.println(wordCount);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
