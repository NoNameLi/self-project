package cn.peng.minispring.test;

import cn.peng.minispring.servlet.DispatcherServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ScanPackageTest {

    private String basePackage;

    public ScanPackageTest(String basePackage) {
        this.basePackage = basePackage;
    }

    @Parameterized.Parameters
    public static Collection params() {
        Object[] object = {"cn.peng.minispring"};  //测试数据
        return Arrays.asList(object);
    }

    @Test
    public void testScan() {
        DispatcherServlet ds = new DispatcherServlet();
        try {
            ds.scanBasePackage(basePackage);
            Arrays.toString(ds.getPackageNames().toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
