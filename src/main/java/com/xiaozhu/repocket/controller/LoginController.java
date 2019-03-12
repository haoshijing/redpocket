package com.xiaozhu.repocket.controller;

import com.xiaozhu.repocket.base.ThreadContext;
import com.xiaozhu.repocket.controller.request.login.UserLoginRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.RetCode;
import com.xiaozhu.repocket.controller.response.login.LoginResponse;
import com.xiaozhu.repocket.service.admin.AdminUserService;
import com.xiaozhu.repocket.service.auth.AdminAuthCacheService;
import com.xiaozhu.repocket.service.auth.AdminAuthInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminAuthCacheService cacheService;


    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        LoginResponse loginResponse = new LoginResponse();

        String name = userLoginRequest.getName();
        String password = userLoginRequest.getPassword();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return new ApiResponse<>(RetCode.PARAM_ERROR, "param error", loginResponse);
        }

        Boolean checkOk = adminUserService.checkUser(name, password);

        loginResponse.setSucc(checkOk);
        if (checkOk) {
            String token = UUID.randomUUID().toString().replace("-", "");
            loginResponse.setToken(token);

            AdminAuthInfo adminAuthInfo = new AdminAuthInfo();
            adminAuthInfo.setToken(token);
            adminAuthInfo.setUserName(name);
            adminAuthInfo.setLastActiveTime(System.currentTimeMillis());
            cacheService.setTokenCache(token, adminAuthInfo);
        }
        return new ApiResponse<>(loginResponse);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ApiResponse<Boolean> logout(HttpServletRequest request) {
        AdminAuthInfo adminAuthInfo = (AdminAuthInfo) ThreadContext.getCurrentAdmin();
        cacheService.deleteToken(adminAuthInfo.getToken());
        return new ApiResponse<>(true);
    }
}
