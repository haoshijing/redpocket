/*
 * @(#) BaseQueryRemoteController.java 2019-03-08
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller;

import org.eclipse.jetty.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author haoshijing
 * @version 2019-03-08
 */
public class BaseQueryRemoteController {
    @Autowired
    protected HttpClient httpClient;

    @Value("${queryServerHost}")
    protected String queryServerHost;

    public String getRequestUrl(String method) {
        final String requestUrl =
                new StringBuilder(queryServerHost).append("/").append(method).toString();
        return requestUrl;
    }
}
