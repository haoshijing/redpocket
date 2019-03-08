/*
 * @(#) RechargeRecordRepository.java 2019-03-08
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.RechargeRecordsPo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author haoshijing
 * @version 2019-03-08
 */
public interface RechargeRecordRepository extends JpaRepository<RechargeRecordsPo, String> {
    RechargeRecordsPo findAllByOrderId(String orderId);
}
