/*
 * @(#) PlayerController.java 2019-03-07
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.player;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.request.agent.AgentQueryRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.controller.response.PlayerDataVo;
import com.xiaozhu.repocket.controller.response.player.PlayerListVo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.util.BytesContentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haoshijing
 * @version 2019-03-07
 */
@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerController {

    @Autowired
    private HttpClient httpClient;


    @Value("${queryServerHost}")
    private String queryServerHost;

    @PostMapping("/queryPlayerList")
    public ApiResponse<PageDataBean<PlayerDataVo>> queryAgentData(@RequestBody AgentQueryRequest request){
        JSONObject queryObject = new JSONObject();
        Integer data = (request.getPage() - 1)*request.getLimit();
        queryObject.put("Index",data);
        queryObject.put("Count",request.getLimit());

        final String requestUrl =
                new StringBuilder(queryServerHost).append("/query_playerinfolist").toString();
        try {
            String result = httpClient.POST(requestUrl).content(new BytesContentProvider(queryObject.toJSONString().getBytes()))
                    .send().getContentAsString();
            log.info("result = {}",result);
            BaseRemoteData<List<PlayerDataVo>> baseRemoteData = JSON.parseObject(result, BaseRemoteData.class);
            if (baseRemoteData != null && baseRemoteData.getCode() == 0) {
                if (baseRemoteData.getData().size() > 0) {
                    PageDataBean<PlayerDataVo> pageDataBean = new PageDataBean<>();
                    pageDataBean.setDatas(baseRemoteData.getData());
                    pageDataBean.setTotalCount(baseRemoteData.getTotalCount());
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return new ApiResponse<>(new PageDataBean<>());
    }
}
