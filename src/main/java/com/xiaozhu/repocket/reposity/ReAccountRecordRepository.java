package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.RAccountRecordsPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReAccountRecordRepository extends JpaRepository<RAccountRecordsPo, String> {
    Page<RAccountRecordsPo> findByGuid(Long guid, Pageable pageable);
}
