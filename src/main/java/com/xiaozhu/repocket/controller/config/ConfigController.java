package com.xiaozhu.repocket.controller.config;


import com.alibaba.fastjson.JSON;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.vo.ServerConfigVo;
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

@RequestMapping("/config")
@RestController
@Slf4j
public class ConfigController {

    @Autowired
    private HttpClient httpClient;

    @Value("${queryServerHost}")
    private String queryServerHost;

    @PostMapping("/show")
    public ApiResponse<ServerConfigVo> queryConfig(){
        final String requestUrl =
                new StringBuilder(queryServerHost).append("/query_serverdata").toString();
        try {
            String result = httpClient.POST(requestUrl).send().getContentAsString();
            BaseRemoteData<List<ServerConfigVo>>  baseRemoteData = JSON.parseObject(result,BaseRemoteData.class);
            if(baseRemoteData != null && baseRemoteData.getCode() == 0){
                if(baseRemoteData.getData().size() > 0 ){
                    return new ApiResponse<>(baseRemoteData.getData().get(0));
                }
            }
        }catch (Exception e){
            log.error("",e);
        }
        return new ApiResponse<>();
    }


    @PostMapping("/setConfig")
    public ApiResponse<Boolean> setConfig(@RequestBody ServerConfigVo configVo){
        try{
            final String requestUrl =
                    new StringBuilder(queryServerHost).append("/modify_serverdata").toString();
            String result = httpClient.POST(requestUrl).content(new BytesContentProvider(JSON.toJSONBytes(configVo)),"application/json").send()
            .getContentAsString();
            log.info("setConfig result = {}",result);
        }catch (Exception e){
            log.error("",e);
        }
        return new ApiResponse<>(false);

    }
}
