package cn.peng.studygodpath.frame.log.logging;

import java.util.logging.Level;

public class SimpleDemo {

    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SimpleDemo.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO,"jdk loggin info");
        logger.log(Level.FINE,"jdk loggin fine");
    }

}
