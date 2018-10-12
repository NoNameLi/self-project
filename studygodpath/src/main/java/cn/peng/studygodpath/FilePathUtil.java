package cn.peng.studygodpath;

import org.testng.annotations.Test;

/**
 * Created by remote on 2018/1/12.
 */
public class FilePathUtil {

    public static String getProjectPath(){
        return  FilePathUtil.class.getResource("/").getFile();
    }

    @Test
    public void pathTest(){

        System.out.println(FilePathUtil.class.getResource("/"));
    }
}
