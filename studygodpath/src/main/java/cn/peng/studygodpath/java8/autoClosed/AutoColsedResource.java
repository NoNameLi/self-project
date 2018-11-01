package cn.peng.studygodpath.java8.autoClosed;

public class AutoColsedResource implements AutoCloseable {

    public void dosomethrind(){
        System.out.println("do....");
    }

    @Override
    public void close() throws Exception {
        System.out.println("resource auto colse");
    }
}
