package cn.peng.storm.demo;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordCountApplication {

    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("s1", new LinesSpout());
        builder.setBolt("b1", new LineSplitBolt(), 2).shuffleGrouping("s1");
        builder.setBolt("b2", new WordCountBolt(), 3).fieldsGrouping("b1", new Fields("word"));

        Config config = new Config();
        config.setNumWorkers(2);
        config.setNumAckers(0);
        StormSubmitter.submitTopology("wordCount", config, builder.createTopology());


    }
}
