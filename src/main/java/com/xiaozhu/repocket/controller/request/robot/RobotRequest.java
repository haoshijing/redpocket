package com.xiaozhu.repocket.controller.request.robot;

import lombok.Data;

/**
 * 类的说明
 *
 * @author chensijie
 * @version 1.0
 * @date 3/12/19 3:01 PM
 **/
@Data
public class RobotRequest {
    private String nick;
    private String avatar;
    private Integer money;
    private Integer win;
}
