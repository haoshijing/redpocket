package com.xiaozhu.repocket.controller.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaozhu.repocket.base.BaseRemoteData;
import com.xiaozhu.repocket.controller.response.SeverConfigVo;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
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
    public BaseRemoteData<List<SeverConfigVo>> queryConfig(){
        final String requestUrl =
                new StringBuilder(queryServerHost).append("/query_serverdata").toString();
        try {
            String result = httpClient.POST(requestUrl).send().getContentAsString();
            return JSON.parseObject(result,BaseRemoteData.class);
        }catch (Exception e){
            log.error("",e);
            return new BaseRemoteData<>();
        }
    }
}
