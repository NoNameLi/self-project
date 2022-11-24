package cn.peng.cli.execl.hong;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFileHandler {

    private final File path;
//    private final int deep = 1;
    private final List<String> acceptFileType = Arrays.asList("xls", "xlsx");

    public ReadFileHandler(File path) {
        this.path = path;
    }

    public List<NumFile> getDataFiles() {
        if (!path.exists()) {
            throw new RuntimeException("数据文件夹不存在");
        }
        if (!path.isDirectory()) {
            throw new RuntimeException("数据文件夹不能是文件");
        }
        File[] files = path.listFiles((item) -> item.isFile() && acceptFileType.contains(getFileType(item.getName())));
        List<NumFile> numFiles = new ArrayList<>(files.length);
        for (int i = 0; i < files.length; i++) {
            numFiles.add(new NumFile(i, files[i]));
        }
        return numFiles;
    }

    private String getFileType(String fileName) {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0) {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }

}
