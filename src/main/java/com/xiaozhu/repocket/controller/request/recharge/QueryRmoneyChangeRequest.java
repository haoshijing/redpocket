package com.xiaozhu.repocket.controller.request.recharge;

import com.xiaozhu.repocket.controller.request.BaseQueryRequest;
import lombok.Data;

/**
 * 类的说明
 *
 * @author chensijie
 * @version 1.0
 * @date 3/13/19 11:54 AM
 **/
@Data
public class QueryRmoneyChangeRequest extends BaseQueryRequest {
    private Long playerId;
    private Integer roomId;
    private Long roomGuid;
    private String nick;
    private String reason;
}
