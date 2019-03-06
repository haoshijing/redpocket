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
    private Integer QueryType;    //请求类型(1:玩家id,2:玩家账号)
    private String GuidOrAccount; //	玩家id 或玩家账号
    private String Password;//	新密码
    private String Money;        //新余额
    private Boolean IsAgent;        //是否代理
    private Integer BindGuid;        //绑定的代理id
}
