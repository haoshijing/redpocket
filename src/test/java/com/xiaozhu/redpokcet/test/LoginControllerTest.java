package com.xiaozhu.redpokcet.test;

import com.xiaozhu.repocket.controller.LoginController;
import com.xiaozhu.repocket.controller.request.login.UserLoginRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.login.LoginResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginControllerTest extends BaseApiTest {

    @Autowired
    private LoginController loginController;

    @Test
    public void testLogin(){
        UserLoginRequest request = new UserLoginRequest();
        request.setName("admin");
        request.setPassword("12345678");
        ApiResponse<LoginResponse> responseApiResponse =  loginController.login(request);
        Assert.assertTrue(responseApiResponse.getCode() == 200);

    }
}
