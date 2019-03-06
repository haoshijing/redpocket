/*
 * @(#) PlayerListQueryReq.java 2019-03-06
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
public class PlayerListQueryReq {
    private Integer QuertType;
    private String GuidOrAccount;
}
