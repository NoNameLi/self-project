package cn.peng.studygodpath.java8.autoClosed;

import org.junit.Test;

// 必须用这种方式 才可以自动关闭资源 try{}catch(){} 则不能正常的自动关闭
public class AutoColseableTest {
    @Test
    public void test2() throws Exception {
        try (AutoColsedResource resource = new AutoColsedResource()) {
            resource.dosomethrind();
        }
    }

}
