package cn.peng.studygodpath.java8.runtime;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunTimeHello {


    @Test
    public void codeNum(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void hook(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("88");
            }
        });
//        Runtime.getRuntime().exit(0);
        Runtime.getRuntime().halt(0);
    }

    @Test
    public void memory() {
        System.out.println("jvm 总内存：" + Runtime.getRuntime().totalMemory() / 1024 /1024 + "m");
        System.out.println("jvm 可用内存：" + Runtime.getRuntime().freeMemory() / 1024 /1024 + "m");
        System.out.println("jvm 最大内存：" + Runtime.getRuntime().maxMemory() / 1024 /1024 + "m");
    }

    @Test
    public void exec() {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"java", "-version"});
            InputStreamReader inputStreamReader = new InputStreamReader(p.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            bufferedReader.close();
            p.destroy();
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
