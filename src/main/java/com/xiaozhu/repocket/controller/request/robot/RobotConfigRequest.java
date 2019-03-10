package com.xiaozhu.repocket.controller.request.robot;

import lombok.Data;

@Data
public class RobotConfigRequest {
    private Integer guid;
    private Integer initMoney;
    private Integer winPercent;
}
