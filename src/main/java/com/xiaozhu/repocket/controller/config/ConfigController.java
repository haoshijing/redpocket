package com.xiaozhu.repocket.controller.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.BaseQueryRemoteController;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.vo.ServerConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.eclipse.jetty.client.util.BytesContentProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/config")
@RestController
@Slf4j
public class ConfigController extends BaseQueryRemoteController {


    @GetMapping("/obtainData")
    public ApiResponse<List<JSONObject>> queryConfig() {
        try {
            String result = httpClient.GET(getRequestUrl("query_serverdata")).getContentAsString();
            BaseRemoteData<List<JSONObject>> baseRemoteData = JSON.parseObject(result, BaseRemoteData.class);
            if (baseRemoteData != null && baseRemoteData.getCode() == 0) {
                if (baseRemoteData.getData().size() > 0) {
                    return new ApiResponse<>(baseRemoteData.getData());
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return new ApiResponse<>(Lists.newArrayList());
    }


    @PostMapping("/setConfig")
    public ApiResponse<Boolean> setConfig(@RequestBody ServerConfigVo configVo) {
        try {

            JSONObject updateJson = new JSONObject();
            updateJson.put("Version", configVo.getVersion());
            updateJson.put("AndroidUpdateUrl", configVo.getAndroidUpdateUrl());
            updateJson.put("CreateDefaultMoney", configVo.getCreateDefaultMoney());
            updateJson.put("IOSUpdateUrl", configVo.getIOSUpdateUrl());
            updateJson.put("Notice", configVo.getNotice());
            updateJson.put("ScrollMessage", configVo.getScrollMessage());
            updateJson.put("SoundDownLoadUrl", configVo.getSoundDownLoadUrl());
            updateJson.put("SoundUpLoadUrl", configVo.getSoundUpLoadUrl());
            updateJson.put("WXShareUrl", configVo.getWXShareUrl());
            updateJson.put("ResourceDownLoadUrl", configVo.getResourceDownLoadUrl());
            updateJson.put("UpdateMessage", configVo.getUpdateMessage());


            String result = httpClient.POST(getRequestUrl("modify_serverdata"))
                    .content(new BytesContentProvider(JSON.toJSONBytes(updateJson)),
                            "application/json").send()
                    .getContentAsString();
            log.info("setConfig result = {}", result);
            return new ApiResponse<>(true);
        } catch (Exception e) {
            log.error("", e);
        }
        return new ApiResponse<>(false);

    }
}
