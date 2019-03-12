package com.xiaozhu.repocket.controller.admin;

import com.xiaozhu.repocket.controller.BaseQueryRemoteController;
import com.xiaozhu.repocket.controller.request.BaseQueryRequest;
import com.xiaozhu.repocket.controller.request.admin.AdminCreateRequest;
import com.xiaozhu.repocket.controller.request.admin.AdminDelRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.po.AdminUserPo;
import com.xiaozhu.repocket.service.admin.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController extends BaseQueryRemoteController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/queryAdminData")
    public ApiResponse<PageDataBean> queryAdminData(@RequestBody BaseQueryRequest request) {

        Page<AdminUserPo> page = adminUserService.queryAdminData(request);

        PageDataBean<AdminUserPo> pageDataBean = new PageDataBean<>();
        pageDataBean.setDatas(page.getContent());
        pageDataBean.setTotalCount(page.getTotalPages());

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/delAdminData")
    public ApiResponse<String> delAdminData(@RequestBody AdminDelRequest request) {

        if (adminUserService.delAdminData(request)) {
            return new ApiResponse<String>("ok");
        }
        return new ApiResponse<>("error");
    }

    @PostMapping("/createAdminData")
    public ApiResponse<Boolean> createAdminData(@RequestBody AdminCreateRequest request) {
        if (StringUtils.isEmpty(request.getPassword()) || StringUtils.isEmpty(request.getUsername())) {
            return new ApiResponse(500, "Param Error", false);
        }

        try {

          boolean createOk =   adminUserService.createAdminData(request);
          if(!createOk){
              return new ApiResponse(500, "Username Exist", false);
          }
          return new ApiResponse<>(true);
        } catch (Exception e) {
            return new ApiResponse(500, "Param Error", false);
        }

    }


}
