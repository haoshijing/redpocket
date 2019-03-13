package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.po.RmoneyChangeRecordsPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RmoneyChangeRecordsRepository extends JpaRepository<RmoneyChangeRecordsPo, String> {

    Page<RmoneyChangeRecordsPo> findByGuid(Long Guid, Pageable pageable);
}
