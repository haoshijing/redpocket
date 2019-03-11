package com.xiaozhu.repocket.controller.response.recharge;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class RechargeVo
{
    private String orderId;

    private Long guid;

    private String phoneNumber;

    private Integer rechargeMoney;

    private String rechargeGold;

    private Timestamp createTime;

}
