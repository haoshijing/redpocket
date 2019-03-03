package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.AdminUserPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<AdminUserPo,Integer>{

    AdminUserPo findByUsername(String username);
}
