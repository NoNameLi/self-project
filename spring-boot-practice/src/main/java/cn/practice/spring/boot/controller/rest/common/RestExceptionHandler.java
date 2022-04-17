package cn.practice.spring.boot.controller.rest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "cn.practice.spring.boot.controller.rest")
public class RestExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ApiResponse handlerException(Exception e) {
        logger.error("全局异常", e);
        return ApiResponse.builder().success(false).message(e.getMessage()).build();
    }

}
