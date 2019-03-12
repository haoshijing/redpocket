/*
 * @(#) PlayerController.java 2019-03-07
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.player;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.BaseQueryRemoteController;
import com.xiaozhu.repocket.controller.request.agent.AgentQueryRequest;
import com.xiaozhu.repocket.controller.request.player.ModifyPlayerReq;
import com.xiaozhu.repocket.controller.request.player.PlayerQueryParam;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.controller.response.PlayerDataVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.eclipse.jetty.client.util.BytesContentProvider;
import org.joda.time.DateTime;
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
public class PlayerController extends BaseQueryRemoteController {

    @PostMapping("/queryMemberSetUp")
    public ApiResponse<List<JSONObject>> queryMemberSetUp(@RequestBody PlayerQueryParam playerQueryParam) {
        JSONObject queryObject = new JSONObject();
        String guid = playerQueryParam.getGuid();
        String account = playerQueryParam.getAccount();

        if (StringUtils.isNotEmpty(guid)) {
            queryObject.put("QueryType", "1");
            queryObject.put("GuidOrAccount", guid);
        } else if (StringUtils.isNotEmpty(account)) {
            queryObject.put("QueryType", "2");
            queryObject.put("GuidOrAccount", account);
        } else {
            return new ApiResponse<>(Lists.newArrayList());
        }

        try {
            String result = httpClient.POST(getRequestUrl("query_playerinfo"))
                    .content(new BytesContentProvider(queryObject.toJSONString().getBytes()))
                    .send().getContentAsString();
            if (StringUtils.isNotEmpty(result)) {
                BaseRemoteData<JSONArray> baseRemoteData = JSON.parseObject(result, BaseRemoteData.class);
                if (baseRemoteData != null && baseRemoteData.getCode() == 0) {
                    List<JSONObject> datas = Lists.newArrayList();
                    for (int i = 0; i < baseRemoteData.getData().size(); i++) {
                        JSONObject data = baseRemoteData.getData().getJSONObject(i);
                        if (data.getBoolean("IsAgent")) {
                            data.put("showIsAgent", "Yes");
                        } else {
                            data.put("showIsAgent", "No");
                        }
                        Long createTime = data.getLongValue("CreateTime");
                        data.put("CreateTime", new DateTime(createTime).toString("yyyy-MM-dd HH:mm:ss"));
                        datas.add(data);
                    }
                    return new ApiResponse<>(datas);
                }
            }

        } catch (Exception e) {
            log.error("", e);
        }
        return new ApiResponse<>(Lists.newArrayList());
    }

    @PostMapping("/updatePlayer")
    public ApiResponse<Boolean> updatePlayer(@RequestBody ModifyPlayerReq modifyPlayerReq) {
        JSONObject updateObject = new JSONObject();
        try {
            updateObject.put("QueryType", 1);
            updateObject.put("GuidOrAccount", modifyPlayerReq.getGuid());
            updateObject.put("Password", modifyPlayerReq.getPassword());
            updateObject.put("Money", modifyPlayerReq.getMoney());
            if (StringUtils.equalsIgnoreCase(modifyPlayerReq.getShowIsAgent(), "Yes")) {
                updateObject.put("IsAgent", true);
            } else {
                updateObject.put("IsAgent", false);
            }
            updateObject.put("BindGuid", modifyPlayerReq.getBindGuid());

            String result = httpClient.POST(getRequestUrl("modify_playerinfo"))
                    .content(new BytesContentProvider(JSON.toJSONBytes(updateObject)))
                    .send().getContentAsString();

            JSONObject jsonObject = JSON.parseObject(result);
            return new ApiResponse<>(true);

        } catch (Exception e) {
            return new ApiResponse<>(false);
        }
    }


    @PostMapping("/queryPlayerData")
    public ApiResponse<PageDataBean<JSONObject>> queryAgentData(@RequestBody AgentQueryRequest request) {
        JSONObject queryObject = new JSONObject();
        Integer data = (request.getPage() - 1) * request.getLimit();
        queryObject.put("Index", data);
        queryObject.put("Count", request.getLimit());
        try {
            String result = httpClient.POST(getRequestUrl("query_playerinfolist"))
                    .content(new BytesContentProvider(queryObject.toJSONString().getBytes()))
                    .send().getContentAsString();
            BaseRemoteData<List<JSONObject>> baseRemoteData = JSON.parseObject(result, BaseRemoteData.class);
            if (baseRemoteData != null && baseRemoteData.getCode() == 0) {
                if (baseRemoteData.getData().size() > 0) {
                    PageDataBean<JSONObject> pageDataBean = new PageDataBean<>();
                    pageDataBean.setDatas(baseRemoteData.getData());
                    pageDataBean.getDatas().forEach(jsonObject -> {
                        Long createTime = jsonObject.getLongValue("CreateTime");
                        jsonObject.put("CreateTime", new DateTime(createTime).toString("yyyy-MM-dd HH:mm:ss"));
                    });
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
