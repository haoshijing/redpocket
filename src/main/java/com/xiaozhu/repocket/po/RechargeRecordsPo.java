/*
 * @(#) RechrageRecordsPo.java 2019-03-08
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author haoshijing
 * @version 2019-03-08
 */
@Data
@Table(name = "recharge_records")
@Entity
@ToString
public class RechargeRecordsPo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;

    @Column(name = "guid")
    private Long guid;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "recharge_money")
    private Integer rechargeMoney;

    @Column(name = "recharge_gold")
    private String rechargeGold;

    @Column(name = "create_time")
    private String createTime;
}
