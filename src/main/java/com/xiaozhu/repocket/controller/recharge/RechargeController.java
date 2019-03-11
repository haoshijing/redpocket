package com.xiaozhu.repocket.controller.recharge;

import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.controller.request.recharge.RechargeRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.controller.response.recharge.RechargeVo;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.po.RmoneyChangeRecordsPo;
import com.xiaozhu.repocket.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharge")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping("/queryRechargeData")
    public ApiResponse<PageDataBean> queryRechargeData(@RequestBody RechargeRequest request) {

        Page<RechargeRecordsPo> page = rechargeService.queryRechargeData(request);

        PageDataBean<RechargeRecordsPo> pageDataBean = new PageDataBean<>();
        pageDataBean.setDatas(page.getContent());
        pageDataBean.setTotalCount(page.getTotalPages());

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/queryRmoneyChangeData")
    public ApiResponse<PageDataBean> queryRmoneyChangeData(@RequestBody RechargeRequest request) {

        Page<RmoneyChangeRecordsPo> page = rechargeService.queryRmoneyChangeData(request);

        PageDataBean<RmoneyChangeRecordsPo> pageDataBean = new PageDataBean<>();
        pageDataBean.setDatas(page.getContent());
        pageDataBean.setTotalCount(page.getTotalPages());

        return new ApiResponse<>(pageDataBean);
    }
}
