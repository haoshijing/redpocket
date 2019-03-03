package com.xiaozhu.repocket.controller.response;


import lombok.Data;

import java.util.List;

@Data
public class PageDataBean<T> {
    private List<T> datas;
    private Integer totalCount;
}
