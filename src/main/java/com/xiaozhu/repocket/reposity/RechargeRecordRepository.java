/*
 * @(#) RechargeRecordRepository.java 2019-03-08
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.RechargeRecordsPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author haoshijing
 * @version 2019-03-08
 */
public interface RechargeRecordRepository extends JpaRepository<RechargeRecordsPo, String>,
        JpaSpecificationExecutor<RechargeRecordsPo> {
    RechargeRecordsPo findAllByOrderId(String orderId);
}
