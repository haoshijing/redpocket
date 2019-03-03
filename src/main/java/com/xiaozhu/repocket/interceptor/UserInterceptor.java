package com.xiaozhu.repocket.interceptor;

import com.alibaba.fastjson.JSON;
import com.xiaozhu.repocket.base.ThreadContext;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.RetCode;
import com.xiaozhu.repocket.service.auth.AdminAuthCacheService;
import com.xiaozhu.repocket.service.auth.AdminAuthInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    private static final String Token = "X-TOKEN";

    @Autowired
    private AdminAuthCacheService adminAuthCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader(Token);
        if(StringUtils.isEmpty(token)){
            notLoginResponse(response);
            return false;
        }
        AdminAuthInfo adminAuthInfo = adminAuthCacheService.getByToken(token);
        if(adminAuthInfo == null){
            notLoginResponse(response);
            return false;
        }
        adminAuthInfo.setLastActiveTime(System.currentTimeMillis());
        ThreadContext.bindAdmin(adminAuthInfo);
        return true;
    }

    public ApiResponse notLoginResponse(HttpServletResponse response) throws Exception{
        ApiResponse apiResponse = new ApiResponse(RetCode.NOT_LOGIN,"用户没有登录",null);
        response.setContentType("application/json");
        response.getWriter().print(JSON.toJSONString(apiResponse));
        return apiResponse;
    }


    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ThreadContext.unBindAdmin();
        ThreadContext.remove();
    }


}
