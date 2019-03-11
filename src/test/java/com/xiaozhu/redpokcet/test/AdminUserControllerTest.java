package com.xiaozhu.redpokcet.test;

import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.reposity.AdminRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminUserControllerTest extends BaseApiTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testQuery() {
        System.out.println(adminRepository.deleteByIdAndUsername(2,"admin1111"));
    }
}
