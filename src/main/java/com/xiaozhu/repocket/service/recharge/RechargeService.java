package com.xiaozhu.repocket.service.recharge;

import com.xiaozhu.repocket.controller.request.recharge.QueryRmoneyChangeRequest;
import com.xiaozhu.repocket.controller.request.recharge.RechargeRequest;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.po.RmoneyChangeRecordsPo;
import com.xiaozhu.repocket.reposity.RechargeRecordRepository;
import com.xiaozhu.repocket.reposity.RmoneyChangeRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RechargeService
{
    @Autowired
    private RechargeRecordRepository repository;

    @Autowired
    private RmoneyChangeRecordsRepository rmoneyChangeRecordsRepository;

   public Page<RechargeRecordsPo> queryRechargeData(RechargeRequest request)
   {
       PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(),
               request.getLimit());
       return repository.findByGuid(request.getGuid(), pageRequest);
   }

    public Page<RmoneyChangeRecordsPo> queryRmoneyChangeData(QueryRmoneyChangeRequest request)
    {
        PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(),
                request.getLimit());
        return rmoneyChangeRecordsRepository.findByGuid(request.getGuid(), pageRequest);
    }
}
