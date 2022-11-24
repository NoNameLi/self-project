package cn.peng.cli.execl.hong;


import java.io.File;

public class NumFile {
    private int num;
    private File file;

    public NumFile(int i, File file) {
        this.num = i;
        this.file = file;
    }

    public int getNum() {
        return num;
    }

    public NumFile setNum(int num) {
        this.num = num;
        return this;
    }

    public File getFile() {
        return file;
    }

    public NumFile setFile(File file) {
        this.file = file;
        return this;
    }
}
