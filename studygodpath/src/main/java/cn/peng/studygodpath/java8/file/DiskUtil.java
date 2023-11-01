package cn.peng.studygodpath.java8.file;

import cn.peng.studygodpath.utils.SystemUtil;
import org.testng.annotations.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * 磁盘工具
 */
public class DiskUtil {


    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    @Test
    public void getInfo() {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File[] fileRoots = File.listRoots();
        Arrays.stream(fileRoots).forEach(item -> {
            System.out.printf("磁盘:%s，大小：%s，空闲：%s，可用：%s\n", fileSystemView.getSystemDisplayName(item),
                    formatFileSize(item.getTotalSpace()), formatFileSize(item.getFreeSpace()), formatFileSize(item.getUsableSpace()));
        });
    }


    public void cleanDisk() {
        if (SystemUtil.isWindows()) {

        } else if (SystemUtil.isLinux()) {

        } else if (SystemUtil.isMacOs()) {

        } else {
            throw new RuntimeException("系统不支持");
        }
    }

    public static void main(String[] args) {

    }

    @Test
    public void realClear(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("目标不存在");
        }
        long usableSpace = file.getUsableSpace();
        File tmpFile = new File(path + "/tmp.txt");
        if (tmpFile.createNewFile()) {
            try (FileOutputStream inputStream = new FileOutputStream(tmpFile);) {
                int i = 0, oneMSize = 1024 * 1024;
                byte[] tmp = new byte[oneMSize];
                while (usableSpace > oneMSize) {
                    inputStream.write(tmp);
                    usableSpace -= oneMSize;
                    i++;
                    if (i == 100) {
                        inputStream.flush();
                        i = 0;
                    }
                }
                if (usableSpace > 0) {
                    inputStream.write(tmp, 0, (int) usableSpace);
                    inputStream.flush();
                }
                tmpFile.delete();
            }
        } else {
            throw new RuntimeException("创建缓存文件失败");
        }
    }
}
