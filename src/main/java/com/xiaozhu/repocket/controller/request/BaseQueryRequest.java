package com.xiaozhu.repocket.controller.request;

import lombok.Data;

@Data
public class BaseQueryRequest {
    private Integer page;
    private Integer limit;
    private Long start;
    private Long end;
}
