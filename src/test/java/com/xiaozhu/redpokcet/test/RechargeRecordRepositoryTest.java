/*
 * @(#) RechargeRecordRepositoryTest.java 2019-03-08
 *
 * Copyright 2019 NetEase.com, Inc. All rights reserved.
 */

package com.xiaozhu.redpokcet.test;

import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.reposity.RechargeRecordRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author haoshijing
 * @version 2019-03-08
 */
public class RechargeRecordRepositoryTest extends BaseApiTest {

    @Autowired
    private RechargeRecordRepository rechargeRecordRepository;

    @Test
    public void testPageQuery() {
        PageRequest pageRequest = PageRequest.of(1, 1);
        Page<RechargeRecordsPo> page = rechargeRecordRepository.findAll(pageRequest);

        System.out.println(page.getTotalElements());
    }

    @Test
    public void testQuery() {
        RechargeRecordsPo rechargeRecordsPo = rechargeRecordRepository.findAllByOrderId("1");
        System.out.println(rechargeRecordsPo);
    }
}
