package cn.practice.spring.boot.controller.rest.common;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "cn.practice.spring.boot.controller.rest")
public class RestResponseAdvice implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType
            , ServerHttpRequest request, ServerHttpResponse response) {
        String type = returnType.getGenericParameterType().getTypeName();
        if (Void.TYPE.getTypeName().equals(type)) {
            return ApiResponse.success();
        } else if (ApiResponse.class.getTypeName().equals(type)) {
            return body;
        } else if (String.class.getTypeName().equals(type)) {
            // TODO json转换
            return ApiResponse.builder().data(body).build().toString();
        } else {
            return ApiResponse.builder().data(body).build();
        }
    }
}
