package com.xiaozhu.redpokcet.test;

import com.xiaozhu.repocket.service.daily.DailyDateQueryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DailyServiceTest extends BaseApiTest {

    @Autowired
    private DailyDateQueryService dailyDateQueryService;

    @Test
    public void testQueryBetAmount(){
      Long sum =    dailyDateQueryService.querySumBetAmount("2019/01/29");

        Assert.assertTrue(sum > 500);
    }
}
