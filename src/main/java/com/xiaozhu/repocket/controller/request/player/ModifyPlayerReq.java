/*
 * @(#) ModifyPlayerReq.java 2019-03-06
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.request.player;

import lombok.Data;

/**
 * @author haoshijing
 * @version 2019-03-06
 */
@Data
public class ModifyPlayerReq {
    private String guid; //	玩家id 或玩家账号
    private String password;//	新密码
    private String money;        //新余额
    private String showIsAgent;        //是否代理
    private Integer bindGuid;        //绑定的代理id
}
