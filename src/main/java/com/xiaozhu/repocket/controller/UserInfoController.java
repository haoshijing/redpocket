package com.xiaozhu.repocket.controller;


import com.google.common.collect.Lists;
import com.xiaozhu.repocket.base.ThreadContext;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.user.UserDataResponse;
import com.xiaozhu.repocket.service.auth.AdminAuthCacheService;
import com.xiaozhu.repocket.service.auth.AdminAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private AdminAuthCacheService adminAuthCacheService;

    @GetMapping("/info")
    public ApiResponse<UserDataResponse> getUserInfo(HttpServletRequest request){
        AdminAuthInfo adminAuthInfo = (AdminAuthInfo) ThreadContext.getCurrentAdmin();
        UserDataResponse response = new UserDataResponse();
        response.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        response.setRoles(Lists.newArrayList("admin"));
        response.setIntroduction("");
        response.setName(adminAuthInfo.getUserName());
        return new ApiResponse<>(response);
    }


    @PostMapping("/logout")
    public ApiResponse<Boolean> logout(){
        AdminAuthInfo adminAuthInfo = (AdminAuthInfo) ThreadContext.getCurrentAdmin();
        adminAuthCacheService.deleteToken(adminAuthInfo.getToken());
        return new ApiResponse<>(true);

    }
}
