package com.xiaozhu.repocket.service.daily;

import com.xiaozhu.repocket.controller.response.daily.DailyDetailVo;
import com.xiaozhu.repocket.reposity.ReAccountRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DailyDateQueryService {

    @Autowired
    private ReAccountRecordRepository reAccountRecordRepository;


    public Long querySumBetAmount(String date) {
        Long amount = reAccountRecordRepository.querySumTotalBetAmount(date);
        if (amount == null) {
            amount = 0L;
        }
        return amount;
    }

    public Long querySumSystemEatChips(String date) {
        Long amount = reAccountRecordRepository.querySumSystemEatChips(date);
        if (amount == null) {
            amount = 0L;
        }
        return amount;
    }

    public Long querySumCommissionAmount(String date) {
        Long amount = reAccountRecordRepository.querySumCommissionAmount(date);
        if (amount == null) {
            amount = 0L;
        }
        return amount;
    }

    public Integer queryRoomCount(String date) {
        Integer amount = reAccountRecordRepository.queryRoomCount(date);
        if (amount == null) {
            amount = 0;
        }
        return amount;
    }

    public Long queryBetPlayer(String date) {
        Long amount = reAccountRecordRepository.queryBetPlayer(date);
        if (amount == null) {
            amount = 0L;
        }
        return amount;
    }


    public DailyDetailVo queryDailyDetail(String date) {
        Long betAmount = querySumBetAmount(date);
        Long betPlayer = queryBetPlayer(date);
        DailyDetailVo detailVo = new DailyDetailVo();
        detailVo.setDate(date);
        detailVo.setSumBetAmount(betAmount);
        detailVo.setSumRoomCount(queryRoomCount(date));
        detailVo.setSumPlayerCount(betPlayer);
        detailVo.setSumCommissionAmount(querySumCommissionAmount(date));
        detailVo.setSumSystemEatChips(querySumSystemEatChips(date));
        return detailVo;
    }
}
