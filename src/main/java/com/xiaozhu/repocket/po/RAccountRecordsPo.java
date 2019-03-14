/*
 * @(#) RaccountRecordsPo.java 2019-03-08
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author haoshijing
 * @version 2019-03-08
 */
@Data
@Table(name = "raccount_records")
@Entity
public class RAccountRecordsPo implements Serializable {
    @Column(name = "Date")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String date;

    @Column(name = "Time")
    private String time;

    @Column(name = "RoomID")
    private Integer roomId;

    @Column(name = "PlayerID")
    private Long guid;

    @Column(name = "Round")
    private Integer round;


    @Column(name = "GameRules")
    private String gameRules;

    @Column(name = "Result")
    private Integer result;

    @Column(name = "Amount")
    private Integer amount;

    @Column(name = "WinOrLose")
    private Integer winOrLose;

    @Column(name = "Commission")
    private Double commission;

    @Column(name = "CommissionAmout")
    private Integer commissionAmout;
    @Column(name = "PlayerGain")
    private Integer playerGain;

    @Column(name = "SystemEatChips")
    private Integer systemEatChips;

}
