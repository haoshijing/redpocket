package com.xiaozhu.repocket.controller.response;


import lombok.Data;

/**
 * api报文结构
 *
 * @param <T>
 */
@Data
public class ApiResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public ApiResponse() {
        this.code = RetCode.OK;
    }

    public ApiResponse(T data) {
        this.code = RetCode.OK;
        this.msg = "Succ";
        this.data = data;
    }

    public ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
