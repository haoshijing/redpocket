package com.xiaozhu.repocket.service.admin;


import com.xiaozhu.repocket.base.ThreadContext;
import com.xiaozhu.repocket.controller.request.BaseQueryRequest;
import com.xiaozhu.repocket.controller.request.admin.AdminCreateRequest;
import com.xiaozhu.repocket.controller.request.admin.AdminDelRequest;
import com.xiaozhu.repocket.po.AdminUserPo;
import com.xiaozhu.repocket.reposity.AdminRepository;
import com.xiaozhu.repocket.service.auth.AdminAuthInfo;
import com.xiaozhu.repocket.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class AdminUserService {

    @Autowired
    private AdminRepository adminRepository;

    public Boolean checkUser(String name, String password) {
        AdminUserPo adminUserPo = adminRepository.findByUsername(name);
        if (adminUserPo != null) {
            String encryptPwd = MD5Util.md5(password);
            if (StringUtils.equalsIgnoreCase(encryptPwd, adminUserPo.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public Page<AdminUserPo> queryAdminData(BaseQueryRequest request) {
        PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(), request.getLimit());
        return adminRepository.findAll(pageRequest);
    }

    public Boolean delAdminData(AdminDelRequest request) {
        Integer is_del = adminRepository.deleteByIdAndUsername(request.getId(), request.getUsername());
        if (is_del == 0) {
            return false;
        }
        return true;
    }

    public void createAdminData(AdminCreateRequest request) {

        AdminAuthInfo adminAuthInfo = (AdminAuthInfo) ThreadContext.getCurrentAdmin();
        AdminUserPo admin = new AdminUserPo();
        admin.setUsername(request.getUsername());
        admin.setPassword(MD5Util.md5(request.getPassword()));
        admin.setCreateTime(System.currentTimeMillis());
        admin.setCreateId(adminAuthInfo.getUserName());
        admin.setLastOperator(adminAuthInfo.getUserName());
        adminRepository.save(admin);

    }
}
