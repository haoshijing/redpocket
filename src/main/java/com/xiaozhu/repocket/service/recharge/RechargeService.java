package com.xiaozhu.repocket.service.recharge;

import com.xiaozhu.repocket.controller.request.recharge.RechargeRequest;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.po.RmoneyChangeRecordsPo;
import com.xiaozhu.repocket.reposity.RechargeRecordRepository;
import com.xiaozhu.repocket.reposity.RmoneyChangeRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class RechargeService
{
    @Autowired
    private RechargeRecordRepository repository;

    @Autowired
    private RmoneyChangeRecordsRepository rmoneyChangeRecordsRepository;

   public Page<RechargeRecordsPo> queryRechargeData(RechargeRequest request)
   {
       PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(), request.getLimit());
       return repository.findAll(pageRequest);

   }

    public Page<RmoneyChangeRecordsPo> queryRmoneyChangeData(RechargeRequest request)
    {
        PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(), request.getLimit());
        return rmoneyChangeRecordsRepository.findAll(pageRequest);
    }
}
