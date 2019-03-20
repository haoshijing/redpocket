package com.xiaozhu.redpokcet.test;

import com.xiaozhu.repocket.controller.record.RecordController;
import com.xiaozhu.repocket.controller.request.recharge.RechargeRequest;
import com.xiaozhu.repocket.controller.response.daily.DailyDetailVo;
import com.xiaozhu.repocket.service.daily.DailyDateQueryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DailyServiceTest extends BaseApiTest {

    @Autowired
    private DailyDateQueryService dailyDateQueryService;

    @Autowired
    private RecordController recordController;

    @Test
    public void testQueryBetAmount() {
        DailyDetailVo detailVo = dailyDateQueryService.queryDailyDetail("2019/01/29");

        Assert.assertTrue(detailVo.getSumCommissionAmount() > 500);
    }

    @Test
    public void testQuerySum() {
        RechargeRequest rechargeRequest = new RechargeRequest();
        rechargeRequest.setPlayerId(100202L);
        recordController.queryRechargeSum(rechargeRequest);
    }
}
