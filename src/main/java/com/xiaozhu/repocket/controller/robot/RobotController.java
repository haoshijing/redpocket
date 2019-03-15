package com.xiaozhu.repocket.controller.robot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.BaseQueryRemoteController;
import com.xiaozhu.repocket.controller.request.robot.RobotConfigRequest;
import com.xiaozhu.repocket.controller.request.robot.RobotQueryRequest;
import com.xiaozhu.repocket.controller.request.robot.RobotRequest;
import com.xiaozhu.repocket.controller.request.robot.RobotsToGroupRequest;
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

    @PostMapping("/createRobotPlayer")
    public ApiResponse<Boolean> createRobot(@RequestBody RobotRequest request) {
        JSONArray jsonArray = new JSONArray();
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Nick", request.getNick());
            jsonObject.put("HeadId", request.getAvatar());
            jsonObject.put("InitMoney", request.getMoney());
            jsonObject.put("WinPercent", request.getWin());
            jsonArray.add(jsonObject);

            String result = httpClient.POST(getRequestUrl("addrobots"))
                    .content(new BytesContentProvider(JSON.toJSONBytes(jsonArray))).send().getContentAsString();

            log.info("createRobotPlayer request = {}, result = {}", request, result);
            JSONObject data = JSON.parseObject(result);

            if (data != null) {
                Integer code = data.getIntValue("Code");
                if (code == 0) {
                    return new ApiResponse<>(true);
                } else if (code == 10009) {
                    JSONObject jsonData = data.getJSONArray("Data").getJSONObject(0);
                    if (jsonData != null) {
                        Integer errorCode = jsonData.getInteger("Code");
                        if (errorCode == 10010) {
                            return new ApiResponse(200, "Nick Exist", false);
                        }
                        if (errorCode == 10011) {
                            return new ApiResponse(200, "Account Exist", false);
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("", e);
        }
        return new ApiResponse<>(false);
    }


    @PostMapping("/updateRobotConfig")
    public ApiResponse<Boolean> updateRobotConfig(@RequestBody RobotConfigRequest request) {
        JSONObject updateObject = new JSONObject();
        try {
            updateObject.put("QueryType", 1);
            updateObject.put("Guid", request.getGuid());
            updateObject.put("InitMoney", request.getInitMoney());
            updateObject.put("WinPercent", request.getWinPercent());

            String result = httpClient.POST(getRequestUrl("modify_robotconfig")).content(new BytesContentProvider(JSON.toJSONBytes(updateObject))).send().getContentAsString();
            log.info("updateRobotConfig request = {}, result = {}", request, result);
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
     *
     * @param request
     * @return
     */
    @PostMapping("/createRobotsToGroup")
    public ApiResponse<Boolean> createRobotsToGroup(@RequestBody RobotsToGroupRequest request) {

        try {
            String result = httpClient.POST(getRequestUrl("addrobots"))
                    .content(new BytesContentProvider(JSON.toJSON(request.getData()).toString()))
                    .send()
                    .getContentAsString();
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
