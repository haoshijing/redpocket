package com.xiaozhu.repocket.controller.request.recharge;

import com.xiaozhu.repocket.controller.request.BaseQueryRequest;
import lombok.Data;

@Data
public class RechargeRequest extends BaseQueryRequest {
    private Long playerId;
}
