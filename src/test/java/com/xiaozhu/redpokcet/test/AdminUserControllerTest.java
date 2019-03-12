package com.xiaozhu.redpokcet.test;

import com.xiaozhu.repocket.po.AdminUserPo;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.reposity.AdminRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminUserControllerTest extends BaseApiTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testQuery() {
        System.out.println(adminRepository.deleteByUsername("admin1111"));
    }

    @Test
    public void addAdmin(){
        long  num1 = 111;

        AdminUserPo admin = new AdminUserPo();
        admin.setUsername("name");
        admin.setPassword("password");
        admin.setCreateTime(num1);
        admin.setLastOperator("222");
        System.out.println(adminRepository.save(admin));
    }
}
