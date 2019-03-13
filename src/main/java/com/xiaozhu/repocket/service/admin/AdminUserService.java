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
import org.springframework.data.domain.Sort;
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
                adminRepository.updateLoginTime(name, System.currentTimeMillis());
                return true;
            }
        }


        return false;
    }

    public Page<AdminUserPo> queryAdminData(BaseQueryRequest request) {
        Sort sort = Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(),
                request.getLimit(), sort);

        return adminRepository.findAll(pageRequest);
    }

    public Boolean delAdminData(AdminDelRequest request) {
        Integer is_del = adminRepository.deleteByUsername(request.getUsername());
        if (is_del == 0) {
            return false;
        }
        return true;
    }

    public boolean createAdminData(AdminCreateRequest request) {

        AdminUserPo queryPo = adminRepository.findByUsername(request.getUsername());
        if (queryPo != null) {
            return false;
        }

        AdminAuthInfo adminAuthInfo = (AdminAuthInfo) ThreadContext.getCurrentAdmin();
        AdminUserPo admin = new AdminUserPo();
        admin.setUsername(request.getUsername());
        admin.setPassword(MD5Util.md5(request.getPassword()));
        admin.setCreateTime(System.currentTimeMillis());
        admin.setCreateId(adminAuthInfo.getUserName());
        admin.setLastOperator(adminAuthInfo.getUserName());
        adminRepository.saveAndFlush(admin);
        return true;
    }
}
