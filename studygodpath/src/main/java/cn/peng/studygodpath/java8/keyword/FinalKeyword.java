package cn.peng.studygodpath.java8.keyword;

import java.io.RandomAccessFile;

public class FinalKeyword {

    public final String f = "final";

    public final StringBuilder sb = new StringBuilder("final");


    public void changeFinalValue() {
//        this.f = "change final"; error
//        this.sb = new StringBuilder();
        this.sb.append("change");
    }

    public void changeFunctionParam(int i, final int j, StringBuilder sb, final StringBuilder fsb) {
        i = 0;
//        j = 0;
        sb = new StringBuilder("change");
//        fsb = new StringBuilderer("change");
        fsb.append("change");

    }
}
