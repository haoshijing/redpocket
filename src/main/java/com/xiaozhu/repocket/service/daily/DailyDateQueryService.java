package com.xiaozhu.repocket.service.daily;

import com.xiaozhu.repocket.reposity.ReAccountRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DailyDateQueryService {

    @Autowired
    private ReAccountRecordRepository reAccountRecordRepository;

    public Long querySumBetAmount(String date){
        Long amount = reAccountRecordRepository.querySumTotalBetAmount(date);
        if(amount == null){
            amount = 0L;
        }
        return amount;
    }

    public Integer queryBetPlayer(String date){
        Integer amount = reAccountRecordRepository.queryBetPlayer(date);
        if(amount == null){
            amount = 0;
        }
        return amount;
    }



}
