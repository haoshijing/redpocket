/*
 * @(#) AgentController.java 2019-03-07
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.agent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.BaseQueryRemoteController;
import com.xiaozhu.repocket.controller.request.agent.AgentQueryRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.controller.response.PlayerDataVo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.util.BytesContentProvider;
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
@Slf4j
@RequestMapping("/agent")
public class AgentController extends BaseQueryRemoteController {


    @PostMapping("/queryAgentData")
    public ApiResponse<PageDataBean<PlayerDataVo>> queryAgentData(@RequestBody AgentQueryRequest request) {
        JSONObject queryObject = new JSONObject();
        Integer data = (request.getPage() - 1) * request.getLimit();
        queryObject.put("Index", data);
        queryObject.put("Count", request.getLimit());


        try {
            String result = httpClient.POST(getRequestUrl("agentinfolist"))
                    .content(new BytesContentProvider(queryObject.toJSONString().getBytes()))
                    .send().getContentAsString();
            log.info("result = {}", result);
            BaseRemoteData<List<PlayerDataVo>> baseRemoteData = JSON.parseObject(result, BaseRemoteData.class);
            if (baseRemoteData != null && baseRemoteData.getCode() == 0) {
                if (baseRemoteData.getData().size() > 0) {
                    PageDataBean<PlayerDataVo> pageDataBean = new PageDataBean<>();
                    pageDataBean.setDatas(baseRemoteData.getData());
                    pageDataBean.setTotalCount(baseRemoteData.getTotalCount());
                    return new ApiResponse<>(pageDataBean);
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return new ApiResponse<>(new PageDataBean<>());
    }

}
