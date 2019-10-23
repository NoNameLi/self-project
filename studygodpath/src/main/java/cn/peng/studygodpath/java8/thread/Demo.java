package cn.peng.studygodpath.java8.thread;

public class Demo {


    public static void main(String[] args) {
        Demo demo = new Demo();

        Inc inc = demo.new Inc();
        Dec dec = demo.new Dec();
    }

    class Inc implements Runnable {
        @Override
        public void run() {

        }
    }

    class Dec implements Runnable {
        @Override
        public void run() {

        }
    }
}
