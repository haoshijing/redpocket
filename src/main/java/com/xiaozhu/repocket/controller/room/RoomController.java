/*
 * @(#) RoomController.java 2019-03-07
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.controller.room;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.BaseQueryRemoteController;
import com.xiaozhu.repocket.controller.request.robot.RobotQueryRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/room")
@Slf4j
public class RoomController extends BaseQueryRemoteController {

    @PostMapping("/queryRoomData")
    public ApiResponse<PageDataBean<JSONObject>> queryRoomData(@RequestBody RobotQueryRequest request){
        JSONObject queryObject = new JSONObject();
        Integer data = (request.getPage() - 1) * request.getLimit();
        queryObject.put("Index", data);
        queryObject.put("Count", request.getLimit());


        try {
            String result = httpClient.POST(getRequestUrl("roominfolist"))
                    .content(new BytesContentProvider(queryObject.toJSONString().getBytes()))
                    .send().getContentAsString();
            log.info("queryRoomData result = {}", result);
            BaseRemoteData<List<JSONObject>> baseRemoteData = JSON.parseObject(result, BaseRemoteData.class);
            if (baseRemoteData != null && baseRemoteData.getCode() == 0) {
                if (baseRemoteData.getData().size() > 0) {
                    baseRemoteData.getData().forEach(jsonObject -> {
                        Long createTime = jsonObject.getLongValue("CreateTime");
                        jsonObject.put("CreateTime", new DateTime(createTime).toString("yyyy-MM-dd HH:mm:ss"));
                    });
                    PageDataBean<JSONObject> pageDataBean = new PageDataBean<>();
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
