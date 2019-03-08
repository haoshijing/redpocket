package com.xiaozhu.repocket.controller.request;

import lombok.Data;

@Data
public class BaseQueryRequest {
    private Integer page;
    private Integer limit;
}
