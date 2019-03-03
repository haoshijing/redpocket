package com.xiaozhu.repocket.base;

import lombok.Data;

@Data
public class BaseRemoteData<T> {
    private Integer code;
    private String msg;
    private T data;
    private Integer totalCount;

    public BaseRemoteData(){
        this.code = -1;
        this.data = null;
        this.msg  = "remote Call Error";
    }
}
