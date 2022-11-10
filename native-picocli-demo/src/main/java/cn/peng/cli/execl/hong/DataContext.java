package cn.peng.cli.execl.hong;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Administrator
 * @CreateTime:2022-11-10 10:26
 * QDescription:
 */
public class DataContext {

    public void exec(File directory, int threadNum) throws InterruptedException {
        ReadFileHandler read = new ReadFileHandler(directory);
        List<NumFile> dataFiles = read.getDataFiles();
        List<List<NumFile>> averageAssign = averageAssign(dataFiles, dataFiles.size() / threadNum);

        ResultOutHandler outHandler = new ResultOutHandler();
        List<Thread> threadList = new ArrayList<>(threadNum);
        for (List<NumFile> files : averageAssign) {
            Thread thread = new Thread(new DataHandler(files, outHandler));
            thread.start();
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            thread.join();
        }
    }

    /**
     * 将源List按照指定元素数量拆分为多个List
     */
    private <T> List<List<T>> averageAssign(List<T> source, int splitItemNum) {
        List<List<T>> result = new ArrayList<>();

        if (source != null && source.size() > 0 && splitItemNum > 0) {
            if (source.size() <= splitItemNum) {
                // 源List元素数量小于等于目标分组数量
                result.add(source);
            } else {
                // 计算拆分后list数量
                int splitNum = (source.size() % splitItemNum == 0) ? (source.size() / splitItemNum) : (source.size() / splitItemNum + 1);

                List<T> value;
                for (int i = 0; i < splitNum; i++) {
                    if (i < splitNum - 1) {
                        value = source.subList(i * splitItemNum, (i + 1) * splitItemNum);
                    } else {
                        // 最后一组
                        value = source.subList(i * splitItemNum, source.size());
                    }
                    result.add(value);
                }
            }
        }
        return result;
    }
}
