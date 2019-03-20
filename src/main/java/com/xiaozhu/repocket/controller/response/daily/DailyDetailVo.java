package com.xiaozhu.repocket.controller.response.daily;

import lombok.Data;

@Data
public class DailyDetailVo {
    private String date;
    private Long sumBetAmount;
    private Long sumSystemEatChips;
    private Long sumCommissionAmount;
    private Integer sumRoomCount;
    private Long sumPlayerCount;
}
