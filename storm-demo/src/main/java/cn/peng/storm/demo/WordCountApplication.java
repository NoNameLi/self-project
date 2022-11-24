package cn.peng.storm.demo;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordCountApplication {

    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("s1", new LinesSpout());
        builder.setBolt("b1", new LineSplitBolt(), 2).shuffleGrouping("s1");
        builder.setBolt("b2", new WordCountBolt(), 3).fieldsGrouping("b1", new Fields("word"));

        Config config = new Config();
        if (args != null && args.length > 0) {
            config.setNumWorkers(2);
            StormSubmitter.submitTopologyWithProgressBar(/*名称*/args[0],/*配置*/ config, builder.createTopology());
        } else {
            config.setMaxTaskParallelism(20);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("WordCountTopology", config, builder.createTopology());
            Thread.sleep(10000);
            cluster.shutdown();
        }
    }
}
