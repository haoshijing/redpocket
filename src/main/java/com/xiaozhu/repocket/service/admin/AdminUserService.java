package com.xiaozhu.repocket.service.admin;


import com.xiaozhu.repocket.po.AdminUserPo;
import com.xiaozhu.repocket.reposity.AdminRepository;
import com.xiaozhu.repocket.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminUserService {

    @Autowired
    private AdminRepository adminRepository;
    public Boolean checkUser(String name, String password) {
        AdminUserPo adminUserPo= adminRepository.findByUsername(name);
        if(adminUserPo != null){
            String encryptPwd = MD5Util.md5(password);
            if(StringUtils.equalsIgnoreCase(encryptPwd, adminUserPo.getPassword())){
                return true;
            }
        }
        return false;
    }
}
