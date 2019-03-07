/*
 * @(#) PlayerController.java 2019-03-07
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.player;

import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.controller.response.player.PlayerListVo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoshijing
 * @version 2019-03-07
 */
@RestController
@RequestMapping("/api/member")
@Slf4j
public class PlayerController {

    @Autowired
    private HttpClient httpClient;

    @Value("${queryServerHost}")
    private String queryServerHost;

    @PostMapping("/queryList")
    public ApiResponse<PageDataBean<PlayerListVo>> queryList() {
        final String requestUrl =
                new StringBuilder(queryServerHost).append("/query_playerinfolist").toString();
        return new ApiResponse<>();
    }
}
