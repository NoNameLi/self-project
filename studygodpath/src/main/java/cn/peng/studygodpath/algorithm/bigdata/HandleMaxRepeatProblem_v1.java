package cn.peng.studygodpath.algorithm.bigdata;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc:
 * @Author: bingbing
 * @Date: 2022/5/4 0004 19:19
 * 单线程处理
 */
public class HandleMaxRepeatProblem_v1 {
    public static final int start = 18;
    public static final int end = 70;

    public static final String dir = "D:\\dataDir";

    public static final String FILE_NAME = "D:\\User.dat";

    private static final int threadNums = 20;


    /**
     * key 为年龄，  value为所有的行列表，使用队列
     */
    private static Map<Integer, Vector<String>> valueMap = new ConcurrentHashMap<>();


    /**
     * 存放数据的队列
     */
    private static List<LinkedBlockingQueue<String>> blockQueueLists = new LinkedList<>();


    /**
     * 统计数量
     */
    private static Map<String, AtomicInteger> countMap = new ConcurrentHashMap<>();


    private static Map<Integer, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    // 队列负载均衡
    private static AtomicLong count = new AtomicLong(0);

    /**
     * 开启消费的标志
     */
    private static volatile boolean startConsumer = false;

    /**
     * 消费者运行保证
     */
    private static volatile boolean consumerRunning = true;


    /**
     * 按照 "," 分割数据，并写入到文件里
     */
    static class SplitData {

        public static void splitLine(String lineData) {
            String[] arr = lineData.split("\n");
            for (String str : arr) {
                if (StringUtils.isEmpty(str)) {
                    continue;
                }
                long index = count.get() % threadNums;
                try {
                    // 如果满了就阻塞
                    blockQueueLists.get((int) index).put(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.getAndIncrement();

            }
        }

        /**
         * 按照 x坐标 来分割 字符串，如果切到的字符不为“，”， 那么把坐标向前或者向后移动一位。
         *
         * @param line
         * @param arr  存放x1,x2坐标
         * @return
         */
        public static String splitStr(String line, int[] arr) {

            int startIndex = arr[0];
            int endIndex = arr[1];
            char start = line.charAt(startIndex);
            char end = line.charAt(endIndex);
            if ((startIndex == 0 || start == ',') && end == ',') {
                arr[0] = endIndex + 1;
                arr[1] = arr[0] + line.length() / 3;
                if (arr[1] >= line.length()) {
                    arr[1] = line.length() - 1;
                }
                return line.substring(startIndex, endIndex);
            }

            if (startIndex != 0 && start != ',') {
                startIndex = startIndex - 1;
            }

            if (end != ',') {
                endIndex = endIndex + 1;
            }

            arr[0] = startIndex;
            arr[1] = endIndex;
            if (arr[1] >= line.length()) {
                arr[1] = line.length() - 1;
            }
            return splitStr(line, arr);
        }
    }

    /**
     *  init map
     */

    static {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }

        //每个队列容量为256
        for (int i = 0; i < threadNums; i++) {
            blockQueueLists.add(new LinkedBlockingQueue<>(256));
        }


        for (int i = start; i <= end; i++) {
            try {
                File subFile = new File(dir + "\\" + i + ".dat");
                if (!file.exists()) {
                    subFile.createNewFile();
                }
                countMap.computeIfAbsent(i + "", integer -> new AtomicInteger(0));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                // 读取数据
                readData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                // 开始消费
                startConsumer();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            monitor();
        }).start();


    }


    /**
     * 每隔60s去检查栈是否为空
     */
    private static void monitor() {
        AtomicInteger emptyNum = new AtomicInteger(0);
        while (consumerRunning) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (startConsumer) {
                // 如果所有栈的大小都为0，那么终止进程
                AtomicInteger emptyCount = new AtomicInteger(0);
                for (int i = 0; i < threadNums; i++) {
                    if (blockQueueLists.get(i).size() == 0) {
                        emptyCount.getAndIncrement();
                    }
                }
                if (emptyCount.get() == threadNums) {
                    emptyNum.getAndIncrement();
                    // 如果连续检查指定次数都为空，那么就停止消费
                    if (emptyNum.get() > 12) {
                        consumerRunning = false;
                        System.out.println("消费结束...");
                        try {
                            clearTask();
                        } catch (Exception e) {
                            System.out.println(e.getCause());
                        } finally {
                            System.exit(-1);
                        }
                    }
                }
            }

        }
    }


    private static void readData() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "utf-8"));
        String line;
        long start = System.currentTimeMillis();
        int count = 1;
        while ((line = br.readLine()) != null) {
            // 按行读取，并向队列写入数据
            SplitData.splitLine(line);
            if (count % 100 == 0) {
                System.out.println("读取100行,总耗时间: " + (System.currentTimeMillis() - start) / 1000 + " s");
            }
            count++;
        }

        br.close();
    }

    private static void clearTask() {
        // 清理，同时找出出现字符最大的数
        Integer targetValue = 0;
        String targetKey = null;
        Iterator<Map.Entry<String, AtomicInteger>> entrySetIterator = countMap.entrySet().iterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry<String, AtomicInteger> entry = entrySetIterator.next();
            Integer value = entry.getValue().get();
            String key = entry.getKey();
            if (value > targetValue) {
                targetValue = value;
                targetKey = key;
            }
        }
        System.out.println("数量最多的年龄为:" + targetKey + "数量为：" + targetValue);
        System.exit(-1);
    }

    /**
     * 使用linkedBlockQueue
     *
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private static void startConsumer() throws FileNotFoundException, UnsupportedEncodingException {
        //如果共用一个队列，那么线程不宜过多，容易出现抢占现象
        System.out.println("开始消费...");
        for (int i = 0; i < threadNums; i++) {
            final int index = i;
            // 每一个线程负责一个queue，这样不会出现线程抢占队列的情况。
            new Thread(() -> {
                while (consumerRunning) {
                    startConsumer = true;
                    try {
                        String str = blockQueueLists.get(index).take();
                        countNum(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }

    // 按照arr的大小，运用多线程分割字符串
    private static void countNum(String str) {
        int[] arr = new int[2];
        arr[1] = str.length() / 3;
//        System.out.println("分割的字符串为start位置为:" + arr[0] + ",end位置为:" + arr[1]);
        for (int i = 0; i < 3; i++) {
            final String innerStr = SplitData.splitStr(str, arr);
//            System.out.println("分割的字符串为start位置为:" + arr[0] + ",end位置为:" + arr[1]);
            new Thread(() -> {
                String[] strArray = innerStr.split(",");
                for (String s : strArray) {
                    countMap.computeIfAbsent(s, s1 -> new AtomicInteger(0)).getAndIncrement();
                }
            }).start();
        }
    }
}