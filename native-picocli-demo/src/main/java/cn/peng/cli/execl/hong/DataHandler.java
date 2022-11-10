package cn.peng.cli.execl.hong;


import java.util.List;

public class DataHandler implements Runnable {

    private List<NumFile> dataFiles;

    private ResultOutHandler outHandler;

    public DataHandler(List<NumFile> dataFiles, ResultOutHandler outHandler) {
        this.dataFiles = dataFiles;
        this.outHandler = outHandler;
    }

    @Override
    public void run() {
        for (NumFile file : dataFiles) {
            // 读取 excel
            // 计算结果
            // 输出结果
            Object data = null;
            outHandler.addResult(file.getNum(), data);
        }
    }
}
