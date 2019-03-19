package com.xiaozhu.repocket.controller.response.daily;

import lombok.Data;

@Data
public class DailyDetailVo {
    private Long sumBetAmount;
    private Long sumSystemEatChips;
    private Long sumCommissionAmount;
    private Long sumRoomCount;
    private Long sumPlayerCount;
}
