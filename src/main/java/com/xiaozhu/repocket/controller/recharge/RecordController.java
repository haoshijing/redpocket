package com.xiaozhu.repocket.controller.recharge;

import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.repocket.controller.request.recharge.QueryRmoneyChangeRequest;
import com.xiaozhu.repocket.controller.request.recharge.RechargeRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.controller.response.recharge.RechargeVo;
import com.xiaozhu.repocket.po.RAccountRecordsPo;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.po.RmoneyChangeRecordsPo;
import com.xiaozhu.repocket.reposity.ReAccountRecordRepository;
import com.xiaozhu.repocket.reposity.RechargeRecordRepository;
import com.xiaozhu.repocket.reposity.RmoneyChangeRecordsRepository;
import com.xiaozhu.repocket.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RechargeRecordRepository rechargeRecordRepository;

    @Autowired
    private RmoneyChangeRecordsRepository rmoneyChangeRecordsRepository;


    @Autowired
    private ReAccountRecordRepository reAccountRecordRepository;

    @PostMapping("/queryRAccountRecords")
    public ApiResponse<PageDataBean<RAccountRecordsPo>> queryRAccountRecords(@RequestBody QueryRmoneyChangeRequest request) {

        PageDataBean<RAccountRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("date").descending().and(Sort.by("time").descending());

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(),
                request.getLimit(),sort);

        Page<RAccountRecordsPo> page = reAccountRecordRepository.findByGuid(request.getGuid(),pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(page.getTotalPages());

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/queryRechargeData")
    public ApiResponse<PageDataBean> queryRechargeData(@RequestBody RechargeRequest request) {
        PageDataBean<RechargeRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("createTime").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(),
                request.getLimit(),sort);

        Page<RechargeRecordsPo> page = rechargeRecordRepository.findByGuid(request.getGuid(),pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(page.getTotalPages());

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/queryRmoneyChangeData")
    public ApiResponse<PageDataBean> queryRmoneyChangeData(@RequestBody QueryRmoneyChangeRequest request) {
        PageDataBean<RmoneyChangeRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("dateTime").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1) * request.getLimit(),
                request.getLimit(),sort);

        Page<RmoneyChangeRecordsPo> page = rmoneyChangeRecordsRepository.findByGuid(request.getGuid(),pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(page.getTotalPages());

        return new ApiResponse<>(pageDataBean);
    }
}
