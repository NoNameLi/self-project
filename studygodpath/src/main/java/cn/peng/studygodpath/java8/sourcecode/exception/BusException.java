package cn.peng.studygodpath.java8.sourcecode.exception;

/**
 * Created by remote on 2018/1/22.
 */
public class BusException extends ParentException {

    public BusException(String message) {
        super(message);
    }

    public BusException(String message, Throwable cause) {
        super(message, cause);
    }
}
