package com.xiaozhu.repocket.controller.recharge;

import com.xiaozhu.repocket.controller.request.recharge.QueryRmoneyChangeRequest;
import com.xiaozhu.repocket.controller.request.recharge.RechargeRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.po.RAccountRecordsPo;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.po.RmoneyChangeRecordsPo;
import com.xiaozhu.repocket.reposity.ReAccountRecordRepository;
import com.xiaozhu.repocket.reposity.RechargeRecordRepository;
import com.xiaozhu.repocket.reposity.RmoneyChangeRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Sort sort = Sort.by("date").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1),
                request.getLimit(), sort);
        RAccountRecordsPo queryPo = new RAccountRecordsPo();
        queryPo.setGuid(request.getGuid());
        Example<RAccountRecordsPo> example = Example.of(queryPo);
        Page<RAccountRecordsPo> page = reAccountRecordRepository.findAll(example, pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(Integer.valueOf(String.valueOf(page.getTotalElements())));

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/queryRechargeData")
    public ApiResponse<PageDataBean> queryRechargeData(@RequestBody RechargeRequest request) {
        PageDataBean<RechargeRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("createTime").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1),
                request.getLimit(), sort);
        RechargeRecordsPo queryPo = new RechargeRecordsPo();
        queryPo.setGuid(request.getGuid());
        Example<RechargeRecordsPo> example = Example.of(queryPo);
        Page<RechargeRecordsPo> page = rechargeRecordRepository.findAll(example, pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(Integer.valueOf(String.valueOf(page.getTotalElements())));

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/queryRmoneyChangeData")
    public ApiResponse<PageDataBean> queryRmoneyChangeData(@RequestBody QueryRmoneyChangeRequest request) {
        PageDataBean<RmoneyChangeRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("dateTime").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1),
                request.getLimit(), sort);
        RmoneyChangeRecordsPo queryPo = new RmoneyChangeRecordsPo();
        queryPo.setGuid(request.getGuid());
        Example<RmoneyChangeRecordsPo> example = Example.of(queryPo);
        Page<RmoneyChangeRecordsPo> page = rmoneyChangeRecordsRepository.findAll(example, pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(Integer.valueOf(String.valueOf(page.getTotalElements())));

        return new ApiResponse<>(pageDataBean);
    }
}
