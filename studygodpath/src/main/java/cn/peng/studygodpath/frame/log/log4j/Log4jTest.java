package cn.peng.studygodpath.frame.log.log4j;


import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
    private static final Logger logger = Logger.getLogger(Log4jTest.class);

    private static org.slf4j.Logger sltLogger = LoggerFactory.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        if (logger.isTraceEnabled()) {
            logger.debug("log4j trace message");

        }
        if (logger.isDebugEnabled()) {
            logger.debug("log4j debug message");
        }
        if (logger.isInfoEnabled()) {
            logger.debug("log4j info message");
        }
    }
}
