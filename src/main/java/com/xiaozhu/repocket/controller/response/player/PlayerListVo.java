/*
 * @(#) PlayerListVo.java 2019-03-06
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.response.player;

import lombok.Data;

/**
 * @author haoshijing
 * @version 2019-03-06
 */
@Data
public class PlayerListVo {
    private Integer Guid;            //玩家id
    private String Account;        //玩家账号
    private String Nick;            //玩家昵称
    private String Money;        //余额
    private Boolean IsAgent;        //是否是代理
    private Integer BindGuid;        //绑定的代理id
    private String TotalPay;        //总支出
    private String TotalGain;    //总收入
    private String CreateTime;    //注册时间

}
