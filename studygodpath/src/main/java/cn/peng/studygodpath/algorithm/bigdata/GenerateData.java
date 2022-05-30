package cn.peng.studygodpath.algorithm.bigdata;

import java.io.*;
import java.util.Random;

/**
 * @Desc:
 * @Author: bingbing
 * @Date: 2022/5/4 0004 19:05
 */
public class GenerateData {
    private static Random random = new Random();

    public static int generateRandomData(int start, int end) {
        return random.nextInt(end - start + 1) + start;
    }


    /**
     * 产生10G的 1-1000的数据在D盘
     */
    public void generateData() throws IOException {
        File file = new File("D:\\User.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int start = 18;
        int end = 70;
        long startTime = System.currentTimeMillis();
        BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
        for (long i = 1; i < Integer.MAX_VALUE * 1.7; i++) {
            String data = generateRandomData(start, end) + ",";
            bos.write(data);
            // 每100万条记录成一行，100万条数据大概4M
            if (i % 1000000 == 0) {
                bos.write("\n");
            }
        }
        System.out.println("写入完成! 共花费时间:" + (System.currentTimeMillis() - startTime) / 1000 + " s");
        bos.close();
    }


    public static void main(String[] args) throws IOException {
//        GenerateData generateData = new GenerateData();
//        try {
//            generateData.generateData();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        readData();
    }




    private static void readData() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\User.dat"), "utf-8"));
        String line;
        long start = System.currentTimeMillis();
        int count = 1;
        while ((line = br.readLine()) != null) {
            // 按行读取
//            SplitData.splitLine(line);
            if (count % 100 == 0) {
                System.out.println("读取100行,总耗时间: " + (System.currentTimeMillis() - start) / 1000 + " s");
                System.gc();
            }
            count++;
        }
        br.close();
    }


}