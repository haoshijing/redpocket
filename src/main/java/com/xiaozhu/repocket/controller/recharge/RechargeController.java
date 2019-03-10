package com.xiaozhu.repocket.controller.recharge;

import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recharge")
public class RechargeController {


    @PostMapping("/queryRechargeData")
    public ApiResponse<PageDataBean> queryRechargeData(){
        return new ApiResponse<>();
    }
}
