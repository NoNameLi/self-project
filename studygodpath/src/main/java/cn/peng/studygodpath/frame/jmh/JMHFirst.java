package cn.peng.studygodpath.frame.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime) // 方法平均执行时间
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 设置输出结果时间粒度为微妙
@State(Scope.Thread) // 每个测试线程一个实例
public class JMHFirst {

    @Benchmark
    public String stringConcat() {
        String a = "a";
        String b = "b";
        String c = "c";
        String s = a + b + c;
        return s;
    }
    
    
    

    public static void main(String[] args) throws RunnerException {
        // 使用一个单独进程执行测试 执行warmup 5次，然后测试5次
        Options options = new OptionsBuilder().include(JMHFirst.class.getSimpleName()).forks(1).warmupIterations(5)
                .measurementIterations(5).build();
        new Runner(options).run();
    }
}
