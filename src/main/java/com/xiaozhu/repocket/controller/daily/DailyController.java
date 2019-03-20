package com.xiaozhu.repocket.controller.daily;

import com.xiaozhu.repocket.controller.request.BaseQueryRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.daily.DailyDetailVo;
import com.xiaozhu.repocket.service.daily.DailyDateQueryService;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private DailyDateQueryService dailyDateQueryService;

    @PostMapping("/queryDailyRecords")
    public ApiResponse<List<DailyDetailVo>> queryDailyRecords(@RequestBody BaseQueryRequest request) {
        Long start = request.getStart();
        Long end = request.getEnd();
        List<DailyDetailVo> detailVos = Lists.newArrayList();
        if (start != null && start > 0 && end != null && end > 0) {
            for (Long i = end + DateUtils.MILLIS_PER_DAY; i >= start; i -= DateUtils.MILLIS_PER_DAY) {
                String date = new DateTime(i).toString("yyyy/MM/dd");
                DailyDetailVo detailVo = dailyDateQueryService.queryDailyDetail(date);
                detailVos.add(detailVo);
            }
        }
        return new ApiResponse<>(detailVos);
    }
}
