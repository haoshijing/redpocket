package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.RAccountRecordsPo;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReAccountRecordRepository extends JpaRepository<RAccountRecordsPo, String>,
        JpaSpecificationExecutor<RAccountRecordsPo> {

}
