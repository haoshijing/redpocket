package com.xiaozhu.repocket.controller.daily;

import com.xiaozhu.repocket.controller.request.BaseQueryRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.controller.response.daily.DailyDetailVo;
import com.xiaozhu.repocket.service.daily.DailyDateQueryService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private DailyDateQueryService dailyDateQueryService;

    public ApiResponse<List<DailyDetailVo>> queryDailyRecords(@RequestBody BaseQueryRequest request){
        return new ApiResponse<>(Lists.newArrayList());
    }
}
