package cn.practice.spring.boot.controller.common;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 11:11
 * QDescription:
 */

@Data
@Builder
public class ApiResponse {

    private boolean success;
    private String message;
    private Object data;

    public static ApiResponse success() {
        return ApiResponse.builder().success(true).build();
    }

}
