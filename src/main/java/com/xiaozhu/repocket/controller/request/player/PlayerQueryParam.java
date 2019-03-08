/*
 * @(#) PlayerQueryParam.java 2019-03-08
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.request.player;

import lombok.Data;

/**
 * @author haoshijing
 * @version 2019-03-08
 */
@Data
public class PlayerQueryParam {
    private String guid;
    private String account;
}
