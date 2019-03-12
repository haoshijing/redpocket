package com.xiaozhu.repocket.controller.robot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.BaseQueryRemoteController;
import com.xiaozhu.repocket.controller.request.robot.*;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.client.util.BytesContentProvider;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/robot")
@Slf4j
public class RobotController extends BaseQueryRemoteController {

    @PostMapping("/queryRobotData")
    public ApiResponse<PageDataBean<JSONObject>> queryRobotData(@RequestBody RobotQueryRequest request) {
        JSONObject queryObject = new JSONObject();
        Integer data = (request.getPage() - 1) * request.getLimit();
        queryObject.put("Index", data);
        queryObject.put("Count", request.getLimit());


        try {
            String result = httpClient.POST(getRequestUrl("query_robotlist")).content(new BytesContentProvider(queryObject.toJSONString().getBytes())).send().getContentAsString();
            log.info("robot List result = {}", result);
            BaseRemoteData<List<JSONObject>> baseRemoteData = JSON.parseObject(result, BaseRemoteData.class);
            if (baseRemoteData != null && baseRemoteData.getCode() == 0) {
                if (baseRemoteData.getData().size() > 0) {
                    PageDataBean<JSONObject> pageDataBean = new PageDataBean<>();
                    baseRemoteData.getData().forEach(jsonObject -> {
                        Long createTime = jsonObject.getLong("CreateTime");
                        jsonObject.put("CreateTime", new DateTime(createTime).toString("yyyy-MM-dd HH:mm:ss"));
                    });
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

    @PostMapping("/createRobot")
    public ApiResponse<Boolean> createRobot(@RequestBody RobotConfigRequest request) {
        JSONObject updateObject = new JSONObject();
        try {
            updateObject.put("QueryType", 1);
            updateObject.put("Guid", request.getGuid());
            updateObject.put("InitMoney", request.getInitMoney());
            updateObject.put("WinPercent", request.getWinPercent());

            String result = httpClient.POST(getRequestUrl("modify_playerinfo")).content(new BytesContentProvider(JSON.toJSONBytes(updateObject))).send().getContentAsString();

            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject != null && jsonObject.containsKey("Code") && jsonObject.getIntValue("Code") == 0) {
                return new ApiResponse<>(true);
            } else {
                return new ApiResponse<>(false);
            }

        } catch (Exception e) {
            return new ApiResponse<>(false);
        }
    }


    @PostMapping("/updateRobotConfig")
    public ApiResponse<Boolean> updateRobotConfig(@RequestBody RobotConfigRequest request) {
        JSONObject updateObject = new JSONObject();
        try {
            updateObject.put("QueryType", 1);
            updateObject.put("Guid", request.getGuid());
            updateObject.put("InitMoney", request.getInitMoney());
            updateObject.put("WinPercent", request.getWinPercent());

            String result = httpClient.POST(getRequestUrl("modify_playerinfo")).content(new BytesContentProvider(JSON.toJSONBytes(updateObject))).send().getContentAsString();

            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject != null && jsonObject.containsKey("Code") && jsonObject.getIntValue("Code") == 0) {
                return new ApiResponse<>(true);
            } else {
                return new ApiResponse<>(false);
            }

        } catch (Exception e) {
            return new ApiResponse<>(false);
        }
    }

    /**
     * 批量创建 机器人
     * @param request
     * @return
     */
    @PostMapping("/createRobotsToGroup")
    public ApiResponse<Boolean> createRobotsToGroup(@RequestBody RobotsToGroupRequest request)
    {
        JSONObject updateObject = new JSONObject();

        try {
//            updateObject.put("Nick", 1);
//            updateObject.put("HeadId", );
//            updateObject.put("InitMoney", );
//            updateObject.put("WinPercent", );

            String result = httpClient.POST(getRequestUrl("addrobots")).content(new BytesContentProvider(JSON.toJSONBytes(updateObject))).send().getContentAsString();

            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject != null && jsonObject.containsKey("Code") && jsonObject.getIntValue("Code") == 0) {
                return new ApiResponse<>(true);
            } else {
                return new ApiResponse<>(false);
            }

        } catch (Exception e) {
            return new ApiResponse<>(false);
        }

    }

}
