package com.xiaozhu.repocket.controller.response;


import com.xiaozhu.repocket.po.RechargeRecordsPo;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class PageDataBean<T> {
    private List<T> datas = Lists.newArrayList();
    private Integer totalCount = 0;

}
