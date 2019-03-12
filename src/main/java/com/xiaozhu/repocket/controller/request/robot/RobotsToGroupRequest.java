package com.xiaozhu.repocket.controller.request.robot;

import lombok.Data;

import java.util.List;

/**
 * 批量加入机器人
 *
 * @author chensijie
 * @version 1.0
 * @date 3/12/19 2:56 PM
 **/
@Data
public class RobotsToGroupRequest {
    private List<RobotRequest> data;
}
