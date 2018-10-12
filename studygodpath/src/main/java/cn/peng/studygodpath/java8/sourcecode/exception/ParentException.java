package cn.peng.studygodpath.java8.sourcecode.exception;

/**
 * Created by remote on 2018/1/22.
 */
public class ParentException extends Exception{

    public ParentException(String message) {
        super(message);
    }

    public ParentException(String message, Throwable cause) {
        super(message, cause);
    }
}

