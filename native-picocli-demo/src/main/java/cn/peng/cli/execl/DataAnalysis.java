package cn.peng.cli.execl;

import cn.peng.cli.execl.hong.DataContext;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.jansi.graalvm.AnsiConsole;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * execl 数据分析
 */
@Command(
        name = "dataAnalysis",
        mixinStandardHelpOptions = true,
        version = "V1.0",
        description = "excel 数据分析"
)
public class DataAnalysis implements Callable<Integer> {

    @Parameters(
            index = "0",
            arity = "1",
            description = "数据文件的目录，默认执行目录"
    )
    private File directory = new File(DataAnalysis.class.getClassLoader().getResource("").getPath());

    @Option(
            names = {"-t", "--thread"},
            defaultValue = "10",
            description = "线程数量，默认: ${DEFAULT-VALUE}"
    )
    private int thread;


    public static void main(String... args) {

        int exitCode = 0;
        try (AnsiConsole ansi = AnsiConsole.windowsInstall()) {
            exitCode = new CommandLine(new DataAnalysis()).execute(args);
        }
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // the business logic...
        new DataContext().exec(directory, thread);
        return 0;
    }
}
