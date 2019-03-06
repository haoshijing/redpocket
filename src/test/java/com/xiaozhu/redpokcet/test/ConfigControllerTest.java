package com.xiaozhu.redpokcet.test;

import com.xiaozhu.repocket.controller.config.ConfigController;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.vo.ServerConfigVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ConfigControllerTest extends BaseApiTest {

    @Autowired
    private ConfigController configController;

    @Test
    public void testShow() {
        ApiResponse<ServerConfigVo> apiResponse = configController.queryConfig();

        Assert.assertTrue(apiResponse.getCode() == 200);

        System.out.println(apiResponse.getData());
    }


    @Test
    public void testSetConfig() {
        ServerConfigVo serverConfigVo = new ServerConfigVo();
        serverConfigVo.setCreateDefaultMoney(500);
        serverConfigVo.setAddress("http://www.baidu.com");
        ApiResponse<Boolean> apiResponse = configController.setConfig(serverConfigVo);

        Assert.assertTrue(apiResponse.getCode() == 200);

        System.out.println(apiResponse.getData());

        ApiResponse<ServerConfigVo> serverConfigVoApiResponse = configController.queryConfig();

        Assert.assertTrue(serverConfigVoApiResponse.getCode() == 200);

        System.out.println(serverConfigVoApiResponse.getData());
    }


}
